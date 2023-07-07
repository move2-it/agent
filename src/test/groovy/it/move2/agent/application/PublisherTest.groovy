package it.move2.agent.application

import com.fasterxml.jackson.databind.ObjectMapper
import it.move2.agent.configuration.PublisherConfiguration
import it.move2.agent.ports.Queue
import it.move2.job.JobOffer
import spock.lang.Specification
import spock.lang.Subject

class PublisherTest extends Specification {

    private PublisherConfiguration configuration
    private ObjectMapper objectMapper
    private Queue queue

    @Subject
    Publisher publisher

    def setup() {
        configuration = new PublisherConfiguration(2)
        objectMapper = new ObjectMapper()
        queue = Mock(Queue)
        publisher = new Publisher(configuration, objectMapper, queue)
    }

    def "publish should chunk job offers and publish them to the queue"() {
        given:
        List<JobOffer> jobOffers = JobFixture.createListJobOffers(10)

        when:
        publisher.publish(jobOffers)

        then:
        5 * queue.pub(_)
    }
}
