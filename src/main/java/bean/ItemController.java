package bean;

import entity.Item;
import entity.ItemType;
import facade.ItemFacade;
import facade.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import facade.util.JsfUtil.PersistAction;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@SessionScoped
public class ItemController implements Serializable {

    @EJB
    private ItemFacade ejbFacade;
    private List<Item> items = null;
    private Item selected;
    private List<Item> sectors = null;
    private List<Item> costUnits = null;
    private List<Item> sourcesOfFunds = null;

    private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);

        List<Item> rootItems = completeItems(ItemType.Category, selected);
        for (Item ri : rootItems) {
            TreeNode node1 = new DefaultTreeNode(ri.getName(), root);
            List<Item> sc = completeItems(ItemType.Category, ri);
            for (Item sci : sc) {
                TreeNode node2 = new DefaultTreeNode(sci.getName(), node1);
                node1.getChildren().add(node2);
            }

        }

    }
    
    

    
    
    public ItemController() {
    }

    public Item getSelected() {
        return selected;
    }

    public void setSelected(Item selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ItemFacade getFacade() {
        return ejbFacade;
    }

    public Item prepareCreate() {
        selected = new Item();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, "Item Created");
        if (!JsfUtil.isValidationFailed()) {
            items = null;
            sourcesOfFunds = null;
            costUnits = null;
            sectors = null;

        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, "Updated");
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, "Deleted");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;
            sourcesOfFunds = null;
            costUnits = null;
            sectors = null;
// Invalidate list of items to trigger re-query.
        }
    }

    public List<Item> getItems(ItemType type) {
        String j;
        j = "select i from Item i "
                + " where i.type=:t "
                + " order by i.name";
        Map m = new HashMap();
        m.put("t", type);
        return getFacade().findBySQL(j, m);
    }

    public List<Item> completeItems(ItemType it, String qry) {
        String j;
        j = "select i from Item i "
                + " where lower(i.name) like :n ";
        Map m = new HashMap();
        if (it != null) {
            j += " and i.type=:t ";
            m.put("t", it);
        }

        j += " order by i.name";

        m.put("n", "%" + qry.toLowerCase() + "%");
        return getFacade().findBySQL(j, m);
    }

    public List<Item> completeItems(ItemType it, Item parent) {
        String j;
        j = "select i from Item i where i.type = :it ";
        Map m = new HashMap();
        m.put("it", it);
        if (parent == null) {
            j += " and i.parentItem is null ";
        } else {
            j += " and i.parentItem = :pi ";
            m.put("pi", parent);
        }
        j += " order by i.name";
        return getFacade().findBySQL(j, m);
    }

    public List<Item> itemsOfACategory(Item p) {
        String j;
        j = "select i from Item i "
                + " where i.parentItem = :p ";
        Map m = new HashMap();
        j += " order by i.name";
        m.put("p", p);
        return getFacade().findBySQL(j, m);
    }

    public List<Item> completeItems(String qry) {
        return completeItems(null, qry);
    }

    public List<Item> completeCategories(String qry) {
        return completeItems(ItemType.Category, qry);
    }

    public List<Item> completeProcurementEntities(String qry) {
        return completeItems(ItemType.Procurement_Entity, qry);
    }

    public List<Item> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, "Error");
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, "Error");
            }
        }
    }

    public Item getItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public Item getItem(String name, ItemType type, boolean createNew) {
        String j;
        Map m = new HashMap();
        j = "select a "
                + " from Item a "
                + " where (upper(a.name) =:n)  ";
        if (type != null) {
            j += " and a.type=:t ";
            m.put("t", type);
        }
        m.put("n", name.toUpperCase());
        Item ti = getFacade().findFirstBySQL(j, m);
        if (createNew == true && ti == null) {
            ti = new Item();
            ti.setName(name);
            ti.setCreatedAt(new Date());
            ti.setType(type);
            getFacade().create(ti);
        }
        return ti;
    }

    public List<Item> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Item> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Item> getCostUnits() {
        if (costUnits == null) {
            costUnits = getItems(ItemType.Cost_Unit);
        }
        return costUnits;
    }

    public void setCostUnits(List<Item> costUnits) {
        this.costUnits = costUnits;
    }

    public ItemFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ItemFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public List<Item> getSectors() {
        return sectors;
    }

    public void setSectors(List<Item> sectors) {
        this.sectors = sectors;
    }

    public List<Item> getSourcesOfFunds() {
        return sourcesOfFunds;
    }

    public void setSourcesOfFunds(List<Item> sourcesOfFunds) {
        this.sourcesOfFunds = sourcesOfFunds;
    }

    @FacesConverter(forClass = Item.class)
    public static class ItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemController controller = (ItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemController");
            return controller.getItem(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Item) {
                Item o = (Item) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Item.class.getName()});
                return null;
            }
        }

    }

}
