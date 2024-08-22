package com.example.faceit.schedule;

import com.example.faceit.dto.JobDtoList;
import com.example.faceit.repository.JobRepository;
import com.example.faceit.utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static com.example.faceit.schedule.JobScheduler.JOB_URL;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JobSchedulerTest {

    @InjectMocks
    private JobScheduler jobScheduler;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void fetchJobsSuccessTest() throws JsonProcessingException {
        String response = """
                {
                  "data": [
                    {
                      "slug": "personalsachbearbeiter-weinheim-133985",
                      "company_name": "KOS KLIMA Anlagenbau Projektierung und Service GmbH",
                      "title": "Personalsachbearbeiter (m/w/d)",
                      "description": "<p>KOS KLIMA ist seit über 40 Jahren ein inhabergeführtes Unternehmen, das heute in zweiter Familiengeneration geleitet wird. Unser Team bildet sich aus 50 Mitarbeitern – vom Azubi bis zum Ingenieur. Ihre Expertise setzen wir ein, um die Projekte unserer Kunden zu realisieren und dabei den bestmöglichen Service zu bieten.</p>\\n<h2>Aufgaben</h2>\\n<p>Sie unterstützen die Geschäftsleitung in allen Personalthemen, arbeiten pro aktiv und sorgen für einen reibungslosen Ablauf aller Prozesse und Arbeitsabläufe. Sie arbeiten nach einer ausführlichen Einweisung eigenständig und im Team mit weiteren Kolleginnen und Kollegen aus anderen Abteilungen. Ihr Arbeitswelt ist modern, digital und wird durch definierte Workflows strukturiert. Zu Ihrem Aufgabengebiet gehört u.a.</p>\\n<ul>\\n<li>Klärung vorhandener Personalbedarfe im Unternehmen mit den jeweiligen Stakeholdern (z. B. GF, Leiter Projekt, Leiter Service, Teamleiter) </li>\\n<li>Klärung und Erstellung eines qualifizierten Anforderungsprofils der zu vergebenden Position</li>\\n<li>Formulierung und Gestaltung einer der Vakanz/des Personalbedarfs entsprechenden Stellenausschreibung</li>\\n<li>Auswahl einer geeigneten Veröffentlichungsplattform/Medium zur Stellenpräsentation bzw. Veröffentlichung </li>\\n<li>Prüfung und Vorselektion eingehenden Bewerbungsunterlagen</li>\\n<li>Terminierung und Durchführung von Kurzinterviews/Vorstellungsgesprächen zusammen mit den jeweiligen Stakeholdern sowie der Geschäftsführung </li>\\n<li>Start, Steuerung und Durchführung des Onboarding-Prozesses </li>\\n<li>Meldung von Stamm-, Vertrags-, und Mitarbeiterdaten an Steuerberater </li>\\n<li>Bearbeitung und Prüfung von Arbeitsunfähigkeitsmeldungen, Urlaubs- und Freizeitanträgen, Bescheinigungen (z.B. für Finanzämter, Agentur für Arbeit, Krankenkassen), usw.</li>\\n<li>Erstellung regelmäßiger Newsletter/Mitarbeitermailings mit aktuellen News rund ums Unternehmen </li>\\n<li>Lohn- und Gehaltsabrechnung in Abstimmung mit dem Steuerberater </li>\\n<li>Pflege Zeit- &#x26; Urlaubskonten und deren Bereitstellung für die Abteilungsleiter</li>\\n</ul>\\n<h2>Qualifikation</h2>\\n<p>Womit Sie uns begeistern können? Sie</p>\\n<ul>\\n<li>haben eine erfolgreich abgeschlossene Ausbildung oder ein Studium </li>\\n<li>gestalten Ihr Arbeitsumfeld aktiv mit und bringen hierfür Ihre Ideen und Vorschläge zur Verbesserung ein  </li>\\n<li>haben Kenntnisse in der Anwendung der gängigen MS Office Produkte (Word, Excel, Outlook </li>\\n<li>gewinnen durch Kompetenz und Kundenorientierung das Vertrauen und den Respekt der Kunden (Kunden, Mitarbeiter, Kollegen)</li>\\n<li>können Sachverhalte einfach und verständlich erklären und geben Ihr Wissen adressatengerecht weiter</li>\\n<li>beraten bedarfsorientiert und sind dabei authentisch, nutzen Ihre Erfahrung um Serviceleistungen am Kunden zu verbessern</li>\\n<li>sind auch unter Belastung zuverlässig, verbindlich und freundlich, stehen zu Ihrem Wort und handeln stets im Sinne des Unternehmens </li>\\n<li>übernehmen Verantwortung für übertragene Aufgaben, treffen eigene Entscheidungen im Rahmen Ihrer Vollmachten</li>\\n<li>sind offen für Veränderungen, stellen sich auf neue Situationen schnell ein und verfolgen auch neue Aufgaben mit Energie, Elan und stets zielorientiert </li>\\n<li>gehen strukturiert und methodisch vor, behalten auch bei steigender Komplexität den Überblick</li>\\n<li>erkennen das Wesentliche sowie Zusammenhänge schnell und erarbeiten entsprechende Ergebnisse</li>\\n<li>können mit Stress umgehen, sind belastbar und arbeiten auch in schwierigen Situationen ruhig und zuverlässig</li>\\n</ul>\\n<h2>Benefits</h2>\\n<p> KOS KLIMA ist einzigartig. So einzigartig, dass wir Ihnen als neues Teammitglied alle Vorteile in unserem Unternehmen auf einer Seite zusammengefasst haben: auf unserer Internetseite /zukunft finden Sie alle Vorteile, die Sie bei uns erwarten.</p>\\n<p>Womit wir Sie begeistern möchten</p>\\n<ul>\\n<li>Innovation. Pragmatismus. Persönlichkeit.</li>\\n<li>Menschen die mehr sind als nur Kollegen</li>\\n<li>gegenseitiges Feedback</li>\\n<li>Trainings zur fachlichen und persönlichen Weiterentwicklung</li>\\n<li>modernste Kommunikations- und Arbeitsmittel</li>\\n<li>Hochwertige Ausstattung der Räumlichkeiten (z. B.: höhenverstellbare Schreibtische, Klimatisierung, etc.)</li>\\n<li>moderner und ökologischer Fuhrpark</li>\\n<li>attraktives und marktgerechtes Festgehalt mit Bonuszahlungen</li>\\n<li>betriebliche Altersvorsorge und vermögenswirksame Leistungen</li>\\n<li>Jobrad</li>\\n<li>Betriebliches Gesundheitsmanagement mit Fitness-Studio</li>\\n<li>Betriebliche Krankenzusatzversicherung</li>\\n<li>Zuschuss zum Mittagessen</li>\\n<li>regelmäßige Mitarbeiter-Incentives</li>\\n</ul>\\n<p>Find <a href=\\"https://www.arbeitnow.com/\\">Jobs in Germany</a> on Arbeitnow</a>",
                      "remote": false,
                      "url": "https://www.arbeitnow.com/jobs/companies/kos-klima-anlagenbau-projektierung-und-service-gmbh/personalsachbearbeiter-weinheim-133985",
                      "tags": [
                        "Administration and Secretariat"
                      ],
                      "job_types": [
                        "berufserfahren"
                      ],
                      "location": "Weinheim",
                      "created_at": 1724168944
                    },
                    {
                      "slug": "anlagenmechaniker-erwitte-88714",
                      "company_name": "Hellweg-Sole-Thermen",
                      "title": "Anlagenmechaniker (m/w/d)",
                      "description": "<p><strong>Du hast keine zwei linke Hände und interessierst dich für Technik?</strong></p>\\n<p>Wir suchen zum nächstmöglichen Datum einen motivierten Anlagenmechaniker, der über fundierte Kenntnisse im Bereich der Installation und Wartung verfügt oder technikaffiner Quereinsteiger ist! Wir bieten eine spannende berufliche Herausforderung in einem dynamischen Team.</p>\\n<h2>Aufgaben</h2>\\n<ul>\\n<li>Montage / Abbau von Anlagen</li>\\n<li>Installation, Einstellung und Prüfung von Mess-, Steuer- und Regelungssystemen</li>\\n<li>Installation von Heizkörpern</li>\\n<li>Dämmung von Warmwasserleitungen und Heizungsrohren</li>\\n<li>Abdichtung von Gas- und Wasserleitungen</li>\\n<li>Aufbau energieeffizienter und nachhaltiger Anlagen</li>\\n<li>u.v.m.</li>\\n</ul>\\n<h2>Qualifikation</h2>\\n<ul>\\n<li>Ausbildung zum Anlagenmechaniker (m/w/d) oder gerne auch Quereinsteiger</li>\\n<li>(ausgeprägtes) Verständnis technischer Zusammenhänge</li>\\n<li>handwerkliches Geschick und körperliche Belastbarkeit</li>\\n<li>selbstständige und strukturierte Arbeitsweise</li>\\n<li>Zuverlässigkeit, Flexibilität und Einsatzbereitschaft</li>\\n<li>Teamfähigkeit</li>\\n</ul>\\n<h2>Benefits</h2>\\n<ul>\\n<li>Kostenfreie Nutzung der Therme &#x26; Saunalandschaft</li>\\n<li>Mitarbeiterpreise in unserer Vitaminbar</li>\\n<li>Zuschuss zur betrieblichen Altersvorsorge (in Teilzeit)</li>\\n<li>Job-Rad Leasing (in Teilzeit)</li>\\n</ul>\\n<p>Klingt interessant für dich? Dann freuen wir uns auf deine Bewerbung!</p>\\n<p>Find <a href=\\"https://www.arbeitnow.com/\\">Jobs in Germany</a> on Arbeitnow</a>",
                      "remote": false,
                      "url": "https://www.arbeitnow.com/jobs/companies/hellweg-sole-thermen/anlagenmechaniker-erwitte-88714",
                      "tags": [
                        "Engineering"
                      ],
                      "job_types": [ ],
                      "location": "Erwitte",
                      "created_at": 1724168944
                    },
                   ]
                }   
                """;
        when(restTemplate.getForObject(JOB_URL, String.class)).thenReturn(response);

        JobDtoList jobDtoList = new JobDtoList();
        var jobs = TestUtils.createJobList(10);
        jobDtoList.setJobDtoList(TestUtils.createNewJobsDto(jobs));
        when(objectMapper.readValue(response, JobDtoList.class)).thenReturn(jobDtoList);

        jobScheduler.fetchJobs();

        verify(restTemplate, times(1)).getForObject(JOB_URL, String.class);
        verify(objectMapper, times(1)).readValue(response, JobDtoList.class);
        verify(jobRepository, times(1)).deleteAll();
        verify(jobRepository, times(1)).saveAll(anyList());
    }

    @Test
    void fetchJobsFailTest() throws JsonProcessingException {
        String response = "invalid json";
        when(restTemplate.getForObject(JOB_URL, String.class)).thenReturn(response);

        when(objectMapper.readValue(response, JobDtoList.class)).thenThrow(JsonProcessingException.class);

        jobScheduler.fetchJobs();

        verify(restTemplate, times(1)).getForObject(JOB_URL, String.class);
        verify(objectMapper, times(1)).readValue(response, JobDtoList.class);
        verify(jobRepository, never()).deleteAll();
        verify(jobRepository, never()).saveAll(anyList());
    }
}
