package com.hurryhand.backend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "ADMINS")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Admin extends BaseUser {
}
