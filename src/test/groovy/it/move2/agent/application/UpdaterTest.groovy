package it.move2.agent.application

import com.fasterxml.jackson.databind.ObjectMapper
import it.move2.agent.configuration.PublisherConfiguration
import it.move2.agent.ports.JobBoard
import it.move2.agent.ports.Queue
import spock.lang.Specification
import spock.lang.Subject

class UpdaterTest extends Specification {
    private JobBoard jobBoard1
    private JobBoard jobBoard2
    private Queue queue
    private ObjectMapper objectMapper
    private Publisher publisher

    @Subject
    private Updater updater

    def setup() {
        jobBoard1 = Mock()
        jobBoard2 = Mock()
        queue = Mock()
        objectMapper = new ObjectMapper()
        publisher = new Publisher(new PublisherConfiguration(2), objectMapper, queue)

        updater = new Updater([jobBoard1, jobBoard2], publisher)
    }

    def "should publish jobs from all job boards"() {
        given:
        def jobs1 = [JobFixture.jobOffer(), JobFixture.jobOffer()]
        def jobs2 = [JobFixture.jobOffer(), JobFixture.jobOffer()]

        jobBoard1.getJobs() >> jobs1
        jobBoard2.getJobs() >> jobs2

        when:
        updater.execute()

        then:
        noExceptionThrown()

        and:
        2 * queue.pub(_) >> {}
    }

    def "should not publish jobs when error occurs"() {
        given:
        def jobs1 = [JobFixture.jobOffer(), JobFixture.jobOffer()]

        jobBoard1.getJobs() >> jobs1
        jobBoard2.getJobs() >> { throw new RuntimeException("Test error message") }

        when:
            updater.execute()

        then:
        thrown(RuntimeException)
        0 * queue.pub(_) >> {}
    }
}