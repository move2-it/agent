package it.move2.agent.domain

data class JobOffer(
    val id: String,
    val title: String,
    val companyName: String,
    val experience: String,
    val skills: List<Skill>,
    val employmentType: List<EmploymentType>,
    val remote: Boolean
)

data class Skill(
    val name: String
)

data class EmploymentType(
    val type: String, val salary: Salary?
)

data class Salary(
    val from: Long,
    val to: Long,
    val currency: String,
)