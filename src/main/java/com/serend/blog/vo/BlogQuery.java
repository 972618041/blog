package com.serend.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
