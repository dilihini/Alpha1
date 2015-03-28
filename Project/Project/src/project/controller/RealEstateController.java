/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import java.io.IOException;
import project.util.HouseFile;
import project.model.ListHouse;
import project.util.SortedList;

/**
 *
 * @author NLiyanage
 */
public class RealEstateController {

    private static SortedList list;
    private static ListHouse house;

    public RealEstateController() {
        //singleton design pattern
        if (list == null) {
            list = new SortedList();
        }
    }
    public int getHouseListLength() {
        return list.lengthIs();
    }

    public void resetFile() throws IOException {
        // Load info from house file into list
        HouseFile.reset();
        while (HouseFile.moreHouses()) {
            house = HouseFile.getNextHouse();
            list.insert(house);
        }
// If possible insert info about first house into text fields
        list.reset();
        if (list.lengthIs() != 0) {
            house = (ListHouse) list.getNextItem();
        }
    }

    public void resetList() {
        list.reset();
    }

    public ListHouse getNextHouse() {
        return (ListHouse) list.getNextItem();
    }

    public ListHouse getHouse() {
        return this.house;
    }

    public boolean addHouse(ListHouse house) {
        if (list.isThere(house)) {
            return false;
        } else {
            list.insert(house);
            return true;
        }
    }

    public boolean deleteHouse(ListHouse house) {
        if (list.isThere(house)) {
            list.delete(house);
            return true;
        } else {
            return false;
        }
    }
    public boolean isHouseExisting(ListHouse house){
        return list.isThere(house);
    }
    public ListHouse retrieveHouse(ListHouse house){
        return (ListHouse) list.retrieve(house);
    }
    public void closeFile() {
        try {
            HouseFile.rewrite();
            list.reset();
            int length = list.lengthIs();
            for (int counter = 1; counter <= length; counter++) {
                house = (ListHouse) list.getNextItem();
                HouseFile.putToFile(house);
            }
            HouseFile.close();
        } catch (IOException fileCloseProblem) {
            System.out.println("Exception:" + fileCloseProblem);
        }
    }

}
