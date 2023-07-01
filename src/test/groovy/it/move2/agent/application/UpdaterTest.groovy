package it.move2.agent.application

import com.fasterxml.jackson.databind.ObjectMapper
import it.move2.agent.configuration.PublisherConfiguration
import it.move2.agent.ports.JobBoard
import it.move2.agent.ports.Queue
import it.move2.job.EmploymentType
import it.move2.job.JobOffer
import it.move2.job.Salary
import it.move2.job.Skill
import spock.lang.Specification
import spock.lang.Subject

class UpdaterTest extends Specification {

    @Subject
    private Updater updater

    private Publisher publisher

    private Queue queue

    private JobBoard jobBoard1
    private JobBoard jobBoard2

    def setup() {
        queue = Mock()
        publisher = new Publisher(new PublisherConfiguration(2), new ObjectMapper(), queue)
        jobBoard1 = Mock()
        jobBoard2 = Mock()
        updater = new Updater([jobBoard1, jobBoard2], publisher)
    }


    def "should publish jobs from all job boards"() {

        given:
        jobBoard1.getJobs() >> getJobs1()
        jobBoard2.getJobs() >> getJobs2()
        queue.pub(_) >> {}

        when:
        updater.execute()

        then:
        1 * publisher.publish(_)
    }


    private List<JobBoardImpl> getTestJobBoardList() {
        return [
                new JobBoardImpl([
                        new JobOffer(
                                "1",
                                "Software Engineer",
                                "Example Company",
                                "3+ years",
                                [
                                        new Skill("Java"),
                                        new Skill("Python"),
                                        new Skill("SQL")
                                ],
                                [
                                        new EmploymentType("Full-time", new Salary(50000, 80000, "USD")),
//                                        new EmploymentType("Contract", null)
                                ],
                                true
                        ),
                        new JobOffer(
                                "2",
                                "Data Scientist",
                                "Another Company",
                                "5+ years",
                                [
                                        new Skill("Python"),
                                        new Skill("Machine Learning"),
                                        new Skill("Statistics")
                                ],
                                [
                                        new EmploymentType("Full-time", new Salary(60000, 90000, "USD"))
                                ],
                                false
                        )
                ]),
                new JobBoardImpl([
                        new JobOffer(
                                "1",
                                "Software Engineer",
                                "Example Company",
                                "3+ years",
                                [
                                        new Skill("Java"),
                                        new Skill("Python"),
                                        new Skill("SQL")
                                ],
                                [
                                        new EmploymentType("Full-time", new Salary(50000, 80000, "USD")),
//                                        new EmploymentType("Contract", null)
                                ],
                                true
                        ),
                        new JobOffer(
                                "2",
                                "Data Scientist",
                                "Another Company",
                                "5+ years",
                                [
                                        new Skill("Python"),
                                        new Skill("Machine Learning"),
                                        new Skill("Statistics")
                                ],
                                [
                                        new EmploymentType("Full-time", new Salary(60000, 90000, "USD"))
                                ],
                                false
                        )
                ]),
                new JobBoardImpl([
                        new JobOffer(
                                "3",
                                "Front-end Developer",
                                "ABC Technologies",
                                "2+ years",
                                [
                                        new Skill("HTML"),
                                        new Skill("CSS"),
                                        new Skill("JavaScript")
                                ],
                                [
                                        new EmploymentType("Full-time", new Salary(40000, 60000, "USD")),
//                                        new EmploymentType("Part-time", null)
                                ],
                                true
                        ),
                        new JobOffer(
                                "4",
                                "Marketing Specialist",
                                "XYZ Corporation",
                                "3+ years",
                                [
                                        new Skill("Digital Marketing"),
                                        new Skill("Social Media Management"),
                                        new Skill("Content Creation")
                                ],
                                [
                                        new EmploymentType("Full-time", new Salary(45000, 70000, "USD")),
//                                        new EmploymentType("Freelance", null)
                                ],
                                false
                        )
                ])
        ]
    }

    private List<JobOffer> getJobs1() {
        return [
                new JobOffer(
                        "1",
                        "Software Engineer",
                        "Example Company",
                        "3+ years",
                        [
                                new Skill("Java"),
                                new Skill("Python"),
                                new Skill("SQL")
                        ],
                        [
                                new EmploymentType("Full-time", new Salary(50000, 80000, "USD")),
//                                new EmploymentType("Contract", null)
                        ],
                        true
                ),
                new JobOffer(
                        "2",
                        "Data Scientist",
                        "Another Company",
                        "5+ years",
                        [
                                new Skill("Python"),
                                new Skill("Machine Learning"),
                                new Skill("Statistics")
                        ],
                        [
                                new EmploymentType("Full-time", new Salary(60000, 90000, "USD"))
                        ],
                        false
                )
        ]
    }

    private List<JobOffer> getJobs2() {
        return [
                new JobOffer(
                        "1",
                        "Software Engineer",
                        "Example Company",
                        "3+ years",
                        [
                                new Skill("Java"),
                                new Skill("Python"),
                                new Skill("SQL")
                        ],
                        [
                                new EmploymentType("Full-time", new Salary(50000, 80000, "USD")),
//                                new EmploymentType("Contract", null)
                        ],
                        true
                ),
                new JobOffer(
                        "2",
                        "Data Scientist",
                        "Another Company",
                        "5+ years",
                        [
                                new Skill("Python"),
                                new Skill("Machine Learning"),
                                new Skill("Statistics")
                        ],
                        [
                                new EmploymentType("Full-time", new Salary(60000, 90000, "USD"))
                        ],
                        false
                )
        ]
    }


    class JobBoardImpl implements JobBoard {
        private List<JobOffer> jobOffers

        JobBoardImpl(List<JobOffer> jobOffers) {
            this.jobOffers = jobOffers
        }

        List<JobOffer> getJobs() {
            return this.jobOffers
        }
    }
}