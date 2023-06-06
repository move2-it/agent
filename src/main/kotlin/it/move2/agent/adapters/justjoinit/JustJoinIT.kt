package it.move2.agent.adapters.justjoinit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import it.move2.agent.configuration.AdapterConfiguration
import it.move2.agent.domain.EmploymentType
import it.move2.agent.domain.JobOffer
import it.move2.agent.domain.Salary
import it.move2.agent.domain.Skill
import it.move2.agent.ports.JobBoards
import okhttp3.OkHttpClient
import okhttp3.Request

class JustJoinIT(
    private val client: OkHttpClient, private val mapper: ObjectMapper, private val configuration: AdapterConfiguration
) : JobBoards {

    override fun getJobs(): List<JobOffer> {
        val request = Request.Builder().url(configuration.path).build()
        val response = client.newCall(request).execute()
        val jobOffers: List<JustJointITResponse> = mapper.readValue(response.body?.string()!!)
        return jobOffers.map { it.toModel() }
    }

    private fun JustJointITResponse.toModel(): JobOffer = JobOffer(
        id = this.id,
        title = this.title,
        companyName = this.companyName,
        experience = this.experienceLevel,
        skills = this.skills.map { Skill(it.name) },
        employmentType = this.employmentTypes.map { it ->
            EmploymentType(it.type, it.salary?.let { Salary(it.from, it.to, it.currency) })
        },
        remote = this.remote
    )
}