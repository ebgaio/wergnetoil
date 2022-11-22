package com.wergnet.wergnetoil.api.usercard.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usercard")
public class UserCard {
	
	@Id
	private Long id;
	private String name;
	private String email;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usercard_permission", joinColumns = @JoinColumn(name = "id_usercard")
		, inverseJoinColumns = @JoinColumn(name = "id_permission"))
	private List<Permission> permissions;

}
