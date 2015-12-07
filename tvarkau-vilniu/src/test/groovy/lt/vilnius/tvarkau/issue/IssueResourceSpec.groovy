package lt.vilnius.tvarkau.issue

import lt.vilnius.tvarkau.Application
import lt.vilnius.tvarkau.test.MockMvcSpecification
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
@WebAppConfiguration
class IssueResourceSpec extends MockMvcSpecification {

    def 'should create a new issue resource'() {
        when:
            def response = mvc(post('/issues').content('''
                {
                    "type": "BICYCLE_ROUTES",
                    "description": "Bike road is damaged"
                }
            '''))
        then:
            response.status == CREATED
            response.details.getHeader('Location') =~ '/issues/\\d+'
        and:
            def issuesCollection = mvc(get('/issues')).json()._embedded.issues
            issuesCollection.find { it.description == 'Bike road is damaged' }
    }

}