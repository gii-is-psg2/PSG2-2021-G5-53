package org.springframework.samples.petclinic.api;



public class ItopApi {
	private static final String ITOP_URL = "https://demo.combodo.com/simple";
	private static final String ITOP_USER = "admin";
	private static final String ITOP_PWD = "admin";
	private static final String TICKET_CLASS = "UserRequest";
	private static final String TITLE = "Service down on %(host)s";
	private static final String DESCRIPTION = "The service %(service)s is down on %(host)s";
	private static final String COMMENT = "Created from Python";
	
	
	
	
	
}
//#!/usr/bin/python
//import requests
//import json
//import sys
// 
//if len(sys.argv) != 5:
//        print "Usage: "+sys.argv[0]+" host service service_status service_state_type\n"
//        sys.exit()
//else:
//        print str(sys.argv)
//        host = sys.argv[1]
//        service = sys.argv[2]
//        service_status = sys.argv[3]
//        service_state_type = sys.argv[4]
// 
//if (service_status != "OK") and (service_status != "UP") and (service_state_type == "HARD" ):
//        json_data = {
//                'operation': 'core/create',
//                'class': TICKET_CLASS,
//                'fields': {
//                        'title': TITLE % {'host': host },
//                        'description': DESCRIPTION % {'host': host, 'service': service },
//                        'org_id': 'SELECT Organization AS O JOIN FunctionalCI AS CI ON CI.org_id = O.id WHERE CI.name="%(host)s"' % {'host': host},
//                        'functionalcis_list': [ {
//                                'functionalci_id': "SELECT FunctionalCI WHERE name='%(host)s'" % {'host': host},
//                                'impact_code': 'manual',
//                        }],
//                },
//                'comment': COMMENT,
//                'output_fields': 'id',
//        }
//        encoded_data = json.dumps(json_data)
//        r = requests.post(ITOP_URL+'/webservices/rest.php?version=1.0', verify=False, data={'auth_user': ITOP_USER , 'auth_pwd': ITOP_PWD , 'json_data': encoded_data})
//        result = json.loads(r.text);
//        if result['code'] == 0:
//                print "Ticket created.\n"
//        else:
//                print result['message']+"\n"
//else:
//        print "Service state type !='HARD', doing nothing.\n"
