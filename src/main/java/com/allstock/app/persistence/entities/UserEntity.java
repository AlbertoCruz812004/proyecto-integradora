package com.allstock.app.persistence.entities;

import java.util.HashSet;
import java.util.Set;

import com.allstock.app.persistence.models.RolesEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, length = 120)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "is_enable", nullable = false)
    private boolean isEnable;

    @Column(name = "account_No_Expired", nullable = false)
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked", nullable = false)
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired", nullable = false)
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<RolesEntity> roles = new HashSet<>();

    @PrePersist
    protected void prePersist() {
        if (!this.isEnable) {
            this.isEnable = true;
        }
        if (!this.accountNoExpired) {
            this.accountNoExpired = true;
        }
        if (!this.accountNoLocked) {
            this.accountNoLocked = true;
        }
        if (!this.credentialNoExpired) {
            this.credentialNoExpired = true;
        }
    }
}
