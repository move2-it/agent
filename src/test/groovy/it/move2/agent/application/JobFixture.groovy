package it.move2.agent.application

import it.move2.job.EmploymentType
import it.move2.job.JobOffer
import it.move2.job.Skill

class JobFixture {

    static jobOffer(args = [:]) {
        new JobOffer(
                'id' in args.keySet() ? args.id as String : 'test-id',
                'title' in args.keySet() ? args.title as String : 'test-title',
                'companyName' in args.keySet() ? args.companyName as String : 'test-company',
                'experience' in args.keySet() ? args.experience as String : 'test-experience',
                'skills' in args.keySet() ? args.skills as List<Skill> : [],
                'employmentType' in args.keySet() ? args.employmentType as List<EmploymentType> : [],
                'remote' in args.keySet() ? args.remote as boolean : true
        )
    }

    static List<JobOffer> createListJobOffers(int count) {
        return (1..count).collect { _ ->
            jobOffer()
        }
    }
}