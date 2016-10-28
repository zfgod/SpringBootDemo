package com.example.study.conditional;

/**
 * author: zf
 * Date: 2016/10/28  17:23
 * Description:
 */
public class LinuxListService implements ListService{
        @Override
        public String showListCmd() {
                return "ls";
        }
}
