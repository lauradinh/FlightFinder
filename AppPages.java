  // --== CS400 File Header Information ==--
// Name: Letong Dai
// Email: ldai29@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.util.List;

/*
* this class provides methods to create html pages
* @author Letong Dai
* */
public class AppPages {

    /*
     * return the search html page (main page) of the app
     * @return string of the search page
     * */
    public static String searchPage() {
        String type = "Content-type: text/html\n\n";
        // head
        //style
        String style = "<style>\n" +
                "        #title {\n" +
                "            text-align: center;\n" +
                "            font-size: 50px;\n" +
                "            height: 200px;\n" +
                "        }\n" +
                "        form {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        p, input, label {\n" +
                "            font-size: 25px;\n" +
                "            display: inline;\n" +
                "        }\n" +
                "    </style>";
        //title
        String head = "<title>Flight Finder</title>\n" + style;
        // body
        String body = "<h1 id=\"title\">Welcome to the Flight Finder!</h1>\n" +
                "<form action=\"flight_finder.cgi\" method=\"get\">\n" +
                "<input type=\"search\" placeholder=\"please enter a start airport\" name=\"start\">\n" +
                "<input type=\"search\" placeholder=\"please enter a end airport\" name=\"end\">\n" +
                "<br>\n" +
                "<p>select path base on: </p>" +
                "<input type=\"radio\" name=\"type\" value=\"0\" id=\"time\">" +
                "<label for=\"time\">time</label>" +
                "<input type=\"radio\" name=\"type\" value=\"1\" id=\"cost\">" +
                "<label for=\"cost\">cost</label>" +
                "<input type=\"radio\" name=\"type\" value=\"2\" id=\"distance\">\n" +
                "<label for=\"distance\">distance</label>" +
                "<br>" +
                "<input type=\"submit\" value=\"search\">\n" +
                "</form>\n";
        return type + "<!DOCTYPE html><html>\n<head>\n" + head + "</head>\n<body>\n" + body + "</body>\n</html>";
    }

    /*
     * return the page that contains the result page
     * @param flights - the list that contains all flight information of the path
     * @return string of the result html page
     * */
    public static String resultPage(List<Flight> flights) {
        String type = "Content-type: text/html\n\n";
        // path
        String path = "<p>" + flights.get(0).getSource() + "</p>\n"; //<p>flight source</p>
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            //<button onclick="retrieveInfo(i)">-></button>
            path += "<button onclick=\"retrieveInfo(" + i + ")\">-></button>\n<p>" +
                    flights.get(i).getTarget() + "</p>\n";
        }
        // head
        //style
        String style = " <style>\n" +
                "        h1, div {\n" +
                "            text-align:center;\n" +
                "            font-size:50px;\n" +
                "        }\n" +
                "        #back {\n" +
                "            text-align:center;\n" +
                "            font-size:30px;\n" +
                "        }\n" +
                "        p {\n" +
                "            display:inline;\n" +
                "        }\n" +
                "        table, td {\n" +
                "            border: 3px solid black;\n" +
                "            width: 50%;\n" +
                "        }\n" +
                "        table {\n" +
                "\t        text-align: center;\n" +
                "            border-collapse: collapse;\n" +
                "            font-size: 30px;\n" +
                "            margin: auto;\n" +
                "        }" +
                "    </style>\n";
        // js script
        String script = "<script>\n" +
                "        function createTable(flightInfo) {\n" +
                "            var param=['start airport', 'end airport', 'cost', 'time', 'distance'];\n" +
                "            var table=document.createElement('table');\n" +
                "            for (j=0 ; j<5 ; j++) {\n" +
                "                var tr=table.insertRow();\n" +
                "                var td1=tr.insertCell();\n" +
                "                var td2=tr.insertCell();\n" +
                "                td1.innerHTML=param[j];\n" +
                "                td2.innerHTML=flightInfo[param[j]];\n" +
                "            }\n" +
                "            document.getElementsByTagName('div')[0].appendChild(table);\n" +
                "        }\n" +
                "        function retrieveInfo(i) {\n" +
                "            // delete old table\n" +
                "            var oldTable=document.getElementsByTagName('table');\n" +
                "            if(oldTable.length!=0)\n" +
                "                document.getElementsByTagName('div')[0].removeChild(oldTable[0]);\n" +
                "            // add loading label\n" +
                "            var loading=document.createElement('p');\n" +
                "            loading.innerHTML='loading...';\n" +
                "            document.getElementsByTagName('div')[0].appendChild(loading);\n" +
                "            // construct request parameter\n" +
                "            var reqInfo=document.getElementsByTagName('p');\n" +
                "            var request='flight_finder.cgi?info='+reqInfo[i].innerHTML+'-'+reqInfo[i+1].innerHTML;\n" +
                "            console.log(request);\n" +
                "            // retrieve information from the server\n" +
                "            var xHttp=new XMLHttpRequest();\n" +
                "            xHttp.onreadystatechange=function() {\n" +
                "                if (this.readyState==4) {\n" +
                "                    // remove loading label\n" +
                "                    document.getElementsByTagName('div')[0].removeChild(loading);\n" +
                "                    if(this.status==200) {\n" +
                "                        var data=JSON.parse(this.responseText);\n" +
                "                        createTable(data);\n" +
                "                    } else {\n" +
                "                        alert(\"Sorry, Flight Finder cannot load the data.\");\n" +
                "                    }\n" +
                "                }\n" +
                "            };\n" +
                "            xHttp.open('GET', request, true);\n" +
                "            xHttp.send();\n" +
                "        }\n" +
                "    </script>\n";
        //title
        String head = "<title>Result</title>\n" + style + script;
        // body
        // <div>[path]</div><a href="flight_finder.cgi">back</a>
        String body = "<h1>result</h1>\n<div>\n" + path +
                "</div>\n<br>\n<a href=\"flight_finder.cgi\" id=\"back\">back</a>\n";
        return type + "<!DOCTYPE html><html>\n<head>\n" + head + "</head>\n<body>\n" + body + "</body>\n</html>";
    }

    /*
     * return the json String that contains the information of a flight
     * @param info - the flight that are displayed
     * @return json string of the flight information
     * */
    public static String info(Flight info) {
        String type = "Content-type: text/json\n\n";
        String[] params = {"start airport", "end airport", "cost", "time", "distance"};
        String data = "{ \"start airport\":\"" + info.getSource() + "\", " +
                "\"end airport\":\"" + info.getTarget() + "\", " +
                "\"cost\":\"" + info.getPrice() + "\", " +
                "\"time\":\"" + info.getDuration() + "\", " +
                "\"distance\":\"" + info.getDistance() + "\" }";
        return type + data;
    }

    /*
     * return a page that tells the user an error occurs
     * @param errMsg - the error message of the application
     * @return string of the error page
     * */
    public static String errPage(String errMsg) {
        String type = "Content-type: text/html\n\n";
        // head
        String style = "<style>\n" +
                "    p, a{\n" +
                "        font-size: 30px;\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "</style>";
        String head = "<title>Error</title>\n" + style;
        // body
        String body = "<p>" + errMsg + "</p>\n<br>\n<a href=\"flight_finder.cgi\">back</a>\n";
        return type + "<!DOCTYPE html><html>\n<head>\n" + head + "</head>\n<body>\n" + body + "</body>\n</html>";
    }
}
