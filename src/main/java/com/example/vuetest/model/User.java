package com.example.vuetest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description
 *
 * @author zwzhang5
 * @date Created on 2021/3/29 11:02
 */
@NoArgsConstructor
@Data
public class User {
    private int userId;
    private String userName;
    private int userAge;
    private String userJob;
    private String userPhone;

}
