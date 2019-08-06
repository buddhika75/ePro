/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ariyaratne_mhb2017
 */
public class ProjectSuperCategory {
    Item category;
    List<ProjectCategory> projectCategorieses;

    public Item getCategory() {
        return category;
    }

    public void setCategory(Item category) {
        this.category = category;
    }

    public List<ProjectCategory> getProjectCategorieses() {
        if(projectCategorieses==null){
            projectCategorieses = new ArrayList<>();
        }
        return projectCategorieses;
    }

    public void setProjectCategorieses(List<ProjectCategory> projectCategorieses) {
        this.projectCategorieses = projectCategorieses;
    }
    
    
}
