package com.bezkoder.spring.jpa.h2.dto;

import com.bezkoder.spring.jpa.h2.entity.TestimonialPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialDto {
    @NotNull(message = "id field cannot be null")
    private Long id;
    @NotNull(message = "page field cannot be null")
    private TestimonialPage page;
    @NotNull(message = "imageUrl field cannot be null")
    private String imageUrl;


}