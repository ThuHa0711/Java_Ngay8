package com.example.Java_Ngay8;

import java.util.List;

public interface DataPlugin {
    List<? extends DataPoint> fetchData();
}
