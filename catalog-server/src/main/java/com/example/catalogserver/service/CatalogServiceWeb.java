package com.example.catalogserver.service;

import com.example.catalogserver.domain.Clothe;
import com.example.catalogserver.domain.Image;
import com.example.catalogserver.domain.Size;
import com.example.catalogserver.repository.clothe.ClotheRepository;
import com.example.catalogserver.repository.description.DescriptionRepository;
import com.example.catalogserver.repository.image.ImageRepository;
import com.example.catalogserver.repository.size.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogServiceWeb implements CatalogService{
    private final ClotheRepository clotheRepository;
    private final SizeRepository sizeRepository;

    private final DescriptionRepository descriptionRepository;
    private final ImageRepository imageRepository;


    @Override
    public List<Clothe> findClotheBySize(String size) {
        return null;
    }

    //Можно еще упростить
    @Transactional
    @Override
    public Clothe addClothe(Clothe clothe) {
        Set<Size> sizeSet = clothe.getSize();
        Set<Size> findSizeSet = new HashSet<>();

        if(!clothe.getSize().isEmpty() && clothe.getSize() != null){
            clothe.getSize().stream().forEach(s->{
                findSizeSet.add(sizeRepository.getBySizeOrCreate(s).get());
            });
        }

        clothe.setSize(findSizeSet);

        if(clothe.getDescription() != null){
            descriptionRepository.save(clothe.getDescription());
        }


        Clothe clotheSave = clotheRepository.save(clothe);
//        List<Image> images = new ArrayList<>();
//        if(!clothe.getImages().isEmpty() && clothe.getImages() != null){
//            for (Image tempImage : clotheSave.getImages()){
//                tempImage.setClothe(clotheSave);
//                images.add(tempImage);
//            }
//            imageRepository.saveAll(images);
//        }

        return clotheSave;
    }

    @Transactional
    @Override
    public Clothe updateClothe(Clothe updateClothe) {
        Clothe findClothe = clotheRepository.findById(updateClothe.getIdCloth()).orElseThrow(()-> new NoSuchElementException());

        findClothe.setColorCloth(updateClothe.getColorCloth());
        findClothe.setNameCloth(updateClothe.getNameCloth());
        findClothe.setGender(updateClothe.getGender());
        findClothe.setPrice(updateClothe.getPrice());
        findClothe.setQuantity(updateClothe.getQuantity());

        if(updateClothe.getDescription()!=null){
            findClothe.setDescription(descriptionRepository.getByDescriptionOrCreate(
                    updateClothe.getDescription()).orElseThrow(NoSuchElementException::new));
        }

        if(!(updateClothe.getImages().isEmpty()) && updateClothe.getImages()!=null){
            findClothe.setImages(updateClothe.getImages().stream()
                    .map(imageRepository::getByImageOrCreate).map(Optional::get).collect(Collectors.toList()));
        }

        for (Image tempImage: findClothe.getImages()){
            tempImage.setClothe(findClothe);
        }

        if(updateClothe.getSize() != null && !updateClothe.getSize().isEmpty()){
            findClothe.setSize(updateClothe.getSize().stream()
                    .map(sizeRepository::getBySizeOrCreate).map(Optional::get).collect(Collectors.toSet()));
        }


        return clotheRepository.save(findClothe);
    }


}
