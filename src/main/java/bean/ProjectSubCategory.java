/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Item;
import entity.Project;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ariyaratne_mhb2017
 */
public class ProjectSubCategory {
    private Item subCategory;
    private List<Project> projects;

    public Item getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Item subCategory) {
        this.subCategory = subCategory;
    }

    public List<Project> getProjects() {
        if(projects==null){
            projects = new ArrayList<>();
        }
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    
    
    
}
