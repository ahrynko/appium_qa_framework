import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;
import page_object.CnnMobileMainPage;
import page_object.CnnMobileSearchResultsPage;
import page_object.model.ArticleDTO;
import web_services.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class CnnTests extends BaseTest {

    @Test
    public void verifyCnnArticles() {
        getWebDriver().get("https://edition.cnn.com/");
        final CnnMobileMainPage cnnMobileMainPage = new CnnMobileMainPage(getWebDriver());
        cnnMobileMainPage.clickBurgerMenuButton();
        final CnnMobileSearchResultsPage searchResultsPage = cnnMobileMainPage.searchFor("covid-19"); // возврат объекта новой страницы
        final String pageSources = searchResultsPage.getPageHtmlSourceCode(); //получить Html

        final List<ArticleDTO> articles = parseCnnPageSources(pageSources); //expected results

        RestTemplate restTemplate = new RestTemplate();                                   // refactor
        final List<ArticleDTO> actual_results = restTemplate.retrieveArticlesFromApi();   // refactor

        ReflectionAssert.assertReflectionEquals("There is incorrect articles displayed!",  // refactor
                articles , actual_results );

    }

    private List<ArticleDTO> parseCnnPageSources(final String pageSources) {
        final Document cnnArticles = Jsoup.parse(pageSources);  // разпаршен Html в Document
        final Elements articleBlocks = cnnArticles.select("cnn-search__result cnn-search__result--article");  //10 (collection)
        return articleBlocks.stream()
                .map(articleBlock -> {  //переменная
                    final String title = articleBlock.selectFirst("h3 a[href]").text();
                    final String body = articleBlock.selectFirst(".cnn-search__result-body").text();
                    return new ArticleDTO(title, body);
                })
                .collect(Collectors.toList());
    }
}

