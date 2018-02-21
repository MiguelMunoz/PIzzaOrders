package com.disney.miguelmunoz.challenge.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import static com.disney.miguelmunoz.challenge.entities.PojoUtility.*;

/**
 * <p>Created by IntelliJ IDEA.
 * <p>Date: 2/19/18
 * <p>Time: 10:49 PM
 *
 * @author Miguel Mu\u00f1oz
 */
@SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
@Entity
public class CustomerOrder {
  private Integer id;
  private Collection<MenuItemOption> options = new LinkedList<>();
  private Boolean complete = Boolean.FALSE;
  private Date orderTime;
  private Date completeTime;
  private MenuItem menuItem;

  @Id
  @GeneratedValue
  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  // This is annotated with @Fetch to prevent error messages that say this: 
  // "org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags"
  // See https://stackoverflow.com/questions/4334970/hibernate-cannot-simultaneously-fetch-multiple-bags
  @SuppressWarnings("WeakerAccess")
  @Fetch(value = FetchMode.SUBSELECT)
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(
      name = "food_order_to_menu_item_option", 
      joinColumns = @JoinColumn(name = "food_order_id"), 
      inverseJoinColumns = @JoinColumn(name = "menu_item_option_id")
  )
  public Collection<MenuItemOption> getOptions() {
    return options;
  }

  public void setOptions(final Collection<MenuItemOption> options) {
    if (options == null) {
      this.options = new LinkedList<>();
    } else {
      this.options = options;
    }
  }

  public Boolean getComplete() {
    return complete;
  }

  public void setComplete(final Boolean complete) {
    this.complete = complete;
  }

  @Transient
  public BigDecimal getFinalPrice() {
    BigDecimal priceTally = getMenuItem().getItemPrice();
    for (MenuItemOption option : getOptions()) {
      priceTally = priceTally.add(option.getDeltaPrice());
    }
    return priceTally;
  }

//  public void setFinalPrice(final BigDecimal finalPrice) {
//    this.finalPrice = finalPrice;
//  }
//

  @Temporal(TemporalType.TIMESTAMP)
  public Date getOrderTime() {
    return cloneDate(orderTime);
  }

  public void setOrderTime(final Date orderTime) {
    this.orderTime = cloneDate(orderTime);  
  }

  @Temporal(TemporalType.TIMESTAMP)
  public Date getCompleteTime() {
    return cloneDate(completeTime);
  }

  public void setCompleteTime(final Date completeTime) {
    this.completeTime = cloneDate(completeTime);
  }

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "menu_item_id")
  public MenuItem getMenuItem() {
    return menuItem;
  }

  public void setMenuItem(final MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  @Override
  public boolean equals(final Object o) {

    if (this == o) { return true; }
    if (!(o instanceof CustomerOrder)) { return false; } // implicitly checks for null

    final CustomerOrder customerOrder = (CustomerOrder) o;

    final Integer theId = getId();
    return (theId != null) ? theId.equals(customerOrder.getId()) : (customerOrder.getId() == null);
  }

  @Override
  public int hashCode() {
    return (getId() != null) ? getId().hashCode() : 0;
  }
}
