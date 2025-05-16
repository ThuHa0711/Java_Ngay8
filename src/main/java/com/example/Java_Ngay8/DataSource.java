package com.example.Java_Ngay8;

public interface DataSource <T extends DataPoint> {
    T fetchData();
}
