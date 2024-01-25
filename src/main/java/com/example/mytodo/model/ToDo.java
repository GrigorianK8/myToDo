package com.example.mytodo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    private int id;
    private String title;
    private Date createdDate;
    private Date finishDate;
    private int userId;
    private String status;
}
