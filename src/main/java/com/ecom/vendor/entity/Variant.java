package com.ecom.vendor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "variants")
public class Variant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String keyName; // e.g., "COLOR", "RAM"
  private String value; // e.g., "BLUE", "8GB"

  @com.fasterxml.jackson.annotation.JsonIgnore
  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;

  public Variant() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getKeyName() {
    return keyName;
  }

  public void setKeyName(String keyName) {
    this.keyName = keyName;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  // getters & setters
  // ...
}
