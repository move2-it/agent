import spock.lang.Specification

class AbcTest extends Specification {

    def "Def"() {
        expect:
        "Ghi" != "Jkl"
    }
}
