package it.move2.agent.adapters.justjoinit

import com.fasterxml.jackson.annotation.JsonProperty

data class JustJointITResponse(
    val title: String,
    val street: String?,
    val city: String,
    @JsonProperty("country_code")
    val countryCode: String?,
    @JsonProperty("address_text")
    val addressText: String,
    @JsonProperty("marker_icon")
    val markerIcon: String,
    @JsonProperty("workplace_type")
    val workplaceType: String,
    @JsonProperty("company_name")
    val companyName: String,
    @JsonProperty("company_url")
    val companyUrl: String,
    @JsonProperty("company_size")
    val companySize: String,
    @JsonProperty("experience_level")
    val experienceLevel: String,
    val latitude: String,
    val longitude: String,
    @JsonProperty("published_at")
    val publishedAt: String,
    @JsonProperty("remote_interview")
    val remoteInterview: Boolean,
    @JsonProperty("open_to_hire_ukrainians")
    val openToHireUkrainians: Boolean,
    val id: String,
    @JsonProperty("display_offer")
    val displayOffer: Boolean,
    @JsonProperty("employment_types")
    val employmentTypes: List<EmploymentType>,
    @JsonProperty("company_logo_url")
    val companyLogoUrl: String,
    val skills: List<Skill>,
    val remote: Boolean,
    val multilocation: List<Multilocation>,
    @JsonProperty("way_of_apply")
    val wayOfApply: String,
)

data class EmploymentType(
    val type: String,
    val salary: Salary?,
)

data class Salary(
    val from: Long,
    val to: Long,
    val currency: String,
)

data class Skill(
    val name: String,
    val level: Long,
)

data class Multilocation(
    val city: String,
    val street: String?,
    val slug: String,
)
