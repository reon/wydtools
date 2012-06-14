package wyd

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.PrintWriter
import java.util.Iterator
import java.util.Properties

import javax.servlet.ServletConfig
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class WydBootLoader extends HttpServlet {

	private static final long serialVersionUID = 1L

	final Logger _log = LoggerFactory.getLogger(WydBootLoader.class)

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config)
		_log.info('################################################')
		_log.info('Bootstrap init work starts...')

		ServletContext context = this.getServletContext()

		String s = context.getRealPath('/service/BootLoader')
		_log.info("realPath : {}", s)
		s = s.substring(0, s.indexOf('service'))
		context.setAttribute('webHome', s)
		_log.info("webHome  : {}", context.getAttribute('webHome'))

		s = (String) context.getAttribute('webHome')
		s += 'WEB-INF/classes/application.properties'
		this.loadPropertiesInToServletContext(context, new File(s))

		def timeZones = [
			'Chennai' : '(GMT+05:30) Chennai',
			'Hawaii' : '(GMT-10:00) Hawaii',
			'Alaska' : '(GMT-09:00) Alaska',
			'Pacific Time (US &amp; Canada)' : '(GMT-08:00) Pacific Time (US &amp; Canada)',
			'Arizona' : '(GMT-07:00) Arizona',
			'Mountain Time (US &amp; Canada)' : '(GMT-07:00) Mountain Time (US &amp; Canada)',
			'Central Time (US &amp; Canada)' : '(GMT-06:00) Central Time (US &amp; Canada)',
			'Eastern Time (US &amp; Canada)' : '(GMT-05:00) Eastern Time (US &amp; Canada)',
			'Indiana (East)' : '(GMT-05:00) Indiana (East)',
			'International Date Line West' : '(GMT-11:00) International Date Line West',
			'Midway Island' : '(GMT-11:00) Midway Island',
			'Samoa' : '(GMT-11:00) Samoa',
			'Tijuana' : '(GMT-08:00) Tijuana',
			'Chihuahua' : '(GMT-07:00) Chihuahua',
			'Mazatlan' : '(GMT-07:00) Mazatlan',
			'Central America' : '(GMT-06:00) Central America',
			'Guadalajara' : '(GMT-06:00) Guadalajara',
			'Mexico City' : '(GMT-06:00) Mexico City',
			'Monterrey' : '(GMT-06:00) Monterrey',
			'Saskatchewan' : '(GMT-06:00) Saskatchewan',
			'Bogota' : '(GMT-05:00) Bogota',
			'Lima' : '(GMT-05:00) Lima',
			'Quito' : '(GMT-05:00) Quito',
			'Caracas' : '(GMT-04:30) Caracas',
			'Atlantic Time (Canada)' : '(GMT-04:00) Atlantic Time (Canada)',
			'La Paz' : '(GMT-04:00) La Paz',
			'Santiago' : '(GMT-04:00) Santiago',
			'Newfoundland' : '(GMT-03:30) Newfoundland',
			'Brasilia' : '(GMT-03:00) Brasilia',
			'Buenos Aires' : '(GMT-03:00) Buenos Aires',
			'Georgetown' : '(GMT-03:00) Georgetown',
			'Greenland' : '(GMT-03:00) Greenland',
			'Mid-Atlantic' : '(GMT-02:00) Mid-Atlantic',
			'Azores' : '(GMT-01:00) Azores',
			'Cape Verde Is.' : '(GMT-01:00) Cape Verde Is.',
			'Casablanca' : '(GMT) Casablanca',
			'Dublin' : '(GMT) Dublin',
			'Edinburgh' : '(GMT) Edinburgh',
			'Lisbon' : '(GMT) Lisbon',
			'London' : '(GMT) London',
			'Monrovia' : '(GMT) Monrovia',
			'Amsterdam' : '(GMT+01:00) Amsterdam',
			'Belgrade' : '(GMT+01:00) Belgrade',
			'Berlin' : '(GMT+01:00) Berlin',
			'Bern' : '(GMT+01:00) Bern',
			'Bratislava' : '(GMT+01:00) Bratislava',
			'Brussels' : '(GMT+01:00) Brussels',
			'Budapest' : '(GMT+01:00) Budapest',
			'Copenhagen' : '(GMT+01:00) Copenhagen',
			'Ljubljana' : '(GMT+01:00) Ljubljana',
			'Madrid' : '(GMT+01:00) Madrid',
			'Paris' : '(GMT+01:00) Paris',
			'Prague' : '(GMT+01:00) Prague',
			'Rome' : '(GMT+01:00) Rome',
			'Sarajevo' : '(GMT+01:00) Sarajevo',
			'Skopje' : '(GMT+01:00) Skopje',
			'Stockholm' : '(GMT+01:00) Stockholm',
			'Vienna' : '(GMT+01:00) Vienna',
			'Warsaw' : '(GMT+01:00) Warsaw',
			'West Central Africa' : '(GMT+01:00) West Central Africa',
			'Zagreb' : '(GMT+01:00) Zagreb',
			'Athens' : '(GMT+02:00) Athens',
			'Bucharest' : '(GMT+02:00) Bucharest',
			'Cairo' : '(GMT+02:00) Cairo',
			'Harare' : '(GMT+02:00) Harare',
			'Helsinki' : '(GMT+02:00) Helsinki',
			'Istanbul' : '(GMT+02:00) Istanbul',
			'Jerusalem' : '(GMT+02:00) Jerusalem',
			'Kyiv' : '(GMT+02:00) Kyiv',
			'Minsk' : '(GMT+02:00) Minsk',
			'Pretoria' : '(GMT+02:00) Pretoria',
			'Riga' : '(GMT+02:00) Riga',
			'Sofia' : '(GMT+02:00) Sofia',
			'Tallinn' : '(GMT+02:00) Tallinn',
			'Vilnius' : '(GMT+02:00) Vilnius',
			'Baghdad' : '(GMT+03:00) Baghdad',
			'Kuwait' : '(GMT+03:00) Kuwait',
			'Moscow' : '(GMT+03:00) Moscow',
			'Nairobi' : '(GMT+03:00) Nairobi',
			'Riyadh' : '(GMT+03:00) Riyadh',
			'St. Petersburg' : '(GMT+03:00) St. Petersburg',
			'Volgograd' : '(GMT+03:00) Volgograd',
			'Tehran' : '(GMT+03:30) Tehran',
			'Abu Dhabi' : '(GMT+04:00) Abu Dhabi',
			'Baku' : '(GMT+04:00) Baku',
			'Muscat' : '(GMT+04:00) Muscat',
			'Tbilisi' : '(GMT+04:00) Tbilisi',
			'Yerevan' : '(GMT+04:00) Yerevan',
			'Kabul' : '(GMT+04:30) Kabul',
			'Ekaterinburg' : '(GMT+05:00) Ekaterinburg',
			'Islamabad' : '(GMT+05:00) Islamabad',
			'Karachi' : '(GMT+05:00) Karachi',
			'Tashkent' : '(GMT+05:00) Tashkent',
			'Kolkata' : '(GMT+05:30) Kolkata',
			'Mumbai' : '(GMT+05:30) Mumbai',
			'New Delhi' : '(GMT+05:30) New Delhi',
			'Kathmandu' : '(GMT+05:45) Kathmandu',
			'Almaty' : '(GMT+06:00) Almaty',
			'Astana' : '(GMT+06:00) Astana',
			'Dhaka' : '(GMT+06:00) Dhaka',
			'Novosibirsk' : '(GMT+06:00) Novosibirsk',
			'Sri Jayawardenepura' : '(GMT+06:00) Sri Jayawardenepura',
			'Rangoon' : '(GMT+06:30) Rangoon',
			'Bangkok' : '(GMT+07:00) Bangkok',
			'Hanoi' : '(GMT+07:00) Hanoi',
			'Jakarta' : '(GMT+07:00) Jakarta',
			'Krasnoyarsk' : '(GMT+07:00) Krasnoyarsk',
			'Beijing' : '(GMT+08:00) Beijing',
			'Chongqing' : '(GMT+08:00) Chongqing',
			'Hong Kong' : '(GMT+08:00) Hong Kong',
			'Irkutsk' : '(GMT+08:00) Irkutsk',
			'Kuala Lumpur' : '(GMT+08:00) Kuala Lumpur',
			'Perth' : '(GMT+08:00) Perth',
			'Singapore' : '(GMT+08:00) Singapore',
			'Taipei' : '(GMT+08:00) Taipei',
			'Ulaan Bataar' : '(GMT+08:00) Ulaan Bataar',
			'Urumqi' : '(GMT+08:00) Urumqi',
			'Osaka' : '(GMT+09:00) Osaka',
			'Sapporo' : '(GMT+09:00) Sapporo',
			'Seoul' : '(GMT+09:00) Seoul',
			'Tokyo' : '(GMT+09:00) Tokyo',
			'Yakutsk' : '(GMT+09:00) Yakutsk',
			'Adelaide' : '(GMT+09:30) Adelaide',
			'Darwin' : '(GMT+09:30) Darwin',
			'Brisbane' : '(GMT+10:00) Brisbane',
			'Canberra' : '(GMT+10:00) Canberra',
			'Guam' : '(GMT+10:00) Guam',
			'Hobart' : '(GMT+10:00) Hobart',
			'Melbourne' : '(GMT+10:00) Melbourne',
			'Port Moresby' : '(GMT+10:00) Port Moresby',
			'Sydney' : '(GMT+10:00) Sydney',
			'Vladivostok' : '(GMT+10:00) Vladivostok',
			'Magadan' : '(GMT+11:00) Magadan',
			'New Caledonia' : '(GMT+11:00) New Caledonia',
			'Solomon Is.' : '(GMT+11:00) Solomon Is.',
			'Auckland' : '(GMT+12:00) Auckland',
			'Fiji' : '(GMT+12:00) Fiji',
			'Kamchatka' : '(GMT+12:00) Kamchatka',
			'Marshall Is.' : '(GMT+12:00) Marshall Is.',
			'Wellington' : '(GMT+12:00) Wellington',
			'Nuku\'alofa' : '(GMT+13:00) Nuku\'alofa',
		]
		context.setAttribute('globalTimeZones', timeZones)

		_log.info('Bootstrap init work ends...')
		_log.info('################################################')
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		res.setContentType('text/html')
		PrintWriter out = (PrintWriter) res.getWriter()
		ServletContext context = this.getServletContext()
		out.println('webHome          = ' + context.getAttribute('webHome'))
		out.close()
	}

	private void loadPropertiesInToServletContext(ServletContext context,
	File propsFile) {
		try {
			Properties props = new Properties()
			props.load(new FileInputStream(propsFile))
			Iterator i = props.keySet().iterator()
			while (i.hasNext()) {
				String key = (String) i.next()
				Object val = props.get(key)
				context.setAttribute(key, val)
			}
		} catch (Throwable t) {
			_log.error('loading application properties failed...', t)
		}
	}
}