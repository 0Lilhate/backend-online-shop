package com.example.imageservice.service;


import com.example.imageservice.connect.SftpConnect;
import com.example.imageservice.feign.CatalogRestFein;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.schmizz.sshj.xfer.LocalSourceFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{



    private final String localDir;
    private final String remoteDir;
    private final SftpConnect sftpConnect;

    private final CatalogRestFein catalogRestFein;

    public ImageServiceImpl(@Value("${upload.path}") String localDir,
                            @Value("${sftp.remoteDir}") String remoteDir,
                            SftpConnect sftpConnect, CatalogRestFein catalogRestFein) {
        this.localDir = localDir;
        this.remoteDir = remoteDir;
        this.sftpConnect = sftpConnect;
        this.catalogRestFein = catalogRestFein;
    }

    @Override
    public String uploadFile(MultipartFile file, Long id) throws IOException {

        String pathUrl = new ClassPathResource(localDir).getPath();
        SSHClient sshClient = sftpConnect.connect();
        SFTPClient sftpClient = sshClient.newSFTPClient();
        File convFile = convert(file);
        LocalSourceFile localSourceFile = new FileSystemFile(convFile);
        sftpClient.put(localSourceFile, remoteDir + convFile.getName());
        catalogRestFein.uploadImageForClothe(id, file.getOriginalFilename()).getBody();

        //тут нужно удалить
        deleteLocalFile(convFile);

        sftpClient.close();
        sshClient.disconnect();

        return file.getOriginalFilename();
    }

    @Override
    public byte[] downloadFile(String fileName, Long id) throws IOException {

        SSHClient sshClient = sftpConnect.connect();
        SFTPClient sftpClient = sshClient.newSFTPClient();
        String file = catalogRestFein.getImageForClothe(id, fileName);
        if (file == null){
            return null;
        }
        sftpClient.get(remoteDir + file, localDir + file);
        String pathFile = new ClassPathResource(localDir + File.separator + fileName).getPath();
        Path path = Paths.get(pathFile);

        ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(path));

        deleteLocalFile(new File(pathFile));

        sftpClient.close();
        sshClient.disconnect();

        return byteArrayResource.getByteArray();
    }

    @Override
    public String deleteFile(Long id,String fileName) throws IOException {
        SSHClient sshClient = sftpConnect.connect();
        SFTPClient sftpClient = sshClient.newSFTPClient();
        String fileDelete = catalogRestFein.deleteImageForClothe(id, fileName);


        if (fileDelete == null){
            return "file not found";
        }

        sftpClient.rm(remoteDir+ fileDelete);

        return fileName;
    }


    private File convert(MultipartFile multipartFile){
        File convFile = new File(localDir + File.separator + multipartFile.getOriginalFilename());

        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            convFile = null;
        }
        return convFile;
    }

    private void deleteLocalFile(File file){
        file.delete();
    }
}
