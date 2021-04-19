package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {
	
	@NotBlank
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@Column(name = "description")
	private String description;
	
	@NotNull
	@Column(name = "budged_target")
	@Min(0)
	private Double budgetTarget;
	
	@NotBlank
	@Column(name = "organization")
	private String organization;
	
	@NotNull
	@Column(name = "is_closed")
	private Boolean isClosed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBudgetTarget() {
		return budgetTarget;
	}

	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}	

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cause")
	private Set<Donation> donations;
	
	protected Set<Donation> getDonationsInternal() {
	    if (this.donations == null) {
	       this.donations = new HashSet<>();
	    }
	    return this.donations;
	}

	public List<Donation> getDonations() {
	    List<Donation> sortedDonations = new ArrayList<>(getDonationsInternal());
	    PropertyComparator.sort(sortedDonations, new MutableSortDefinition("date", false, false));
	    return Collections.unmodifiableList(sortedDonations);
	}

	public void addDonation(Donation donation) {
	    getDonationsInternal().add(donation);
	    donation.setCause(this);
	}
}