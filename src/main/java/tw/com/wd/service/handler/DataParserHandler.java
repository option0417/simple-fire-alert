package tw.com.wd.service.handler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tw.com.wd.service.obj.FireAlertObj;
import tw.com.wd.service.obj.FireAlert;

import java.util.ArrayList;
import java.util.List;

public class DataParserHandler extends AbstractFireAlertHandler {
    @Override
    protected void processing(FireAlertObj fireAlertObj) {
        Document doc = (Document) fireAlertObj.getData(FireAlertObj.KEY_DATA_DOC);

        if (doc != null) {
            Elements tableElements = doc.getElementsByTag("table");

            Element element = tableElements.get(1);

            Elements trElements = element.getElementsByTag("tr");

            List<FireAlert> fireAlertList = new ArrayList<>(trElements.size() - 2);

            for (int idx = 1; idx < trElements.size(); idx++) {
                element = trElements.get(idx);

                Elements tdElements = element.getElementsByTag("td");

                if (tdElements.size() == 5) {
                    fireAlertList.add(
                            new FireAlert()
                                    .setCaseTime(tdElements.get(0).text())
                                    .setCaseType(tdElements.get(1).text())
                                    .setCaseTeam(tdElements.get(2).text())
                                    .setCaseStatus(tdElements.get(3).text())
                                    .setCaseAddress(tdElements.get(4).text())
                    );
                }
            }

            fireAlertObj.putData(FireAlertObj.KEY_DATA_LIST, fireAlertList);
        }
    }
}
