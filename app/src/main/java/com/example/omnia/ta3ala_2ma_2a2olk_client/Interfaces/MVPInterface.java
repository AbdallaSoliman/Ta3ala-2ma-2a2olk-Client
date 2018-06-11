package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HeshamMuhammed on 6/2/2018.
 */

public interface MVPInterface {

    interface View {

        void showCategories(HashMap<String, List<String>> categoriesName, HashMap<String, List<String>> placesName
                , HashMap<String, List<String>> customerServciceName, HashMap<String, List<Integer>> categoriesId, HashMap<String, List<Integer>> placesId
                , HashMap<String, List<Integer>> customerServciceId);
        void isAdded(String message);
    }

    interface Presenter {
        void showData();
        void AddQuestion(Question question);
    }
}