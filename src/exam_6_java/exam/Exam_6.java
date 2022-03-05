package exam_6_java.exam;

import com.sun.net.httpserver.HttpExchange;
import exam_6_java.exam_server.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class Exam_6 extends BasicServer {
    private final static Configuration freemarker = initFreeMarker();
    public static final String NAME = "name";
//    private static Category category;
//    private static final Category CATEGORY = category;
    private static final String DISCRIPTION =  "discription";
    private List<ModelDay>dayList = new ArrayList<>();
//    private ModelDay day = new ModelDay();
    private List<ModelNote.Note> notes = new ArrayList<>();
    ModelCalendar calendar = new ModelCalendar();
    ModelDay day = new ModelDay(30);



    public Exam_6(String host, int port) throws IOException {
        super(host, port);
        registerGet("/", this::GetCalendar);
        registerGet("/page", this::GetNotes);
//        registerGet("/page", this::deleteTaskAndRender);

    }

    private void GetNotes(HttpExchange exchange) {
//        List<ModelNote> makeNotes = null;
        renderTemplate(exchange, "notes.ftl",getModelNotes());

    }
//    private void handleQueryRequest(HttpExchange exchange) {
//        String queryParams = getBody(exchange);
//        Map<String, String> params = Utils.parseUrlEncoded(queryParams, "&");
//        String dayStr = params.get("day");
//        int day = Integer.parseInt(dayStr);
//        ModelDay byDay = value.findByDay(day);
//        if (byDay.getModelDay() == -1) {
//            redirect303(exchange, "/error");
//        } else {
//            renderTemplate(exchange, "notes.ftl", byDay);
//        }
//    }

    public List<ModelDay> getDayList() {
        return dayList;
    }

    public List<ModelNote.Note> getNotes() {
        return notes;
    }
    private ModelNote.Note addNote(Map<String, String> parsed) {
        ModelNote.Note newNote = ModelNote.Note.makeNotes(parsed.get(NAME),parsed.get(DISCRIPTION));
        notes.add(newNote);
        return newNote;
    }

    private void deleteTaskAndRender(HttpExchange exchange) {
    String queryParams = getQueryParams(exchange);
    Map<String, String> params = Utils.parseUrlEncoded(queryParams, "&");

    String date = params.getOrDefault("date", "0");
    String name = params.getOrDefault("note", "0");

    if (name.equals("0")) {
        if (getDayList().stream().filter(u -> u.getDate().equals(date)).count() > 0) {
            renderTemplate(exchange, "notes.ftl", getDayList().stream().filter(u -> u.getDate().equals(date)).findFirst().get());
        }
    } else {
       getDayList().stream().filter(u -> u.getDate()
                .equals(date))
                .findFirst().get().getNotes()
                .removeIf(t -> t.getName().equals(name));
        renderTemplate(exchange, "notes.ftl", getDayList().stream().filter(u -> u.getDate().equals(date)).findFirst().get());
    }
}


    private void GetCalendar(HttpExchange exchange) {
//        ModelCalendar calendar = new ModelCalendar(dayList);
        renderTemplate(exchange, "calendar.ftl",getModelCalendar(MakeDay()));
    }
    private ModelCalendar getModelCalendar(List<ModelDay> dayList){
        return new ModelCalendar(dayList);
    }
//    int days;
//    Random r = new Random();
//    public ModelDay makeDayList(int days){
//        this.days = days;
//        int rand = r.nextInt(4)+1;
//        for (int i = 0; i <rand; i++) {
//            notes.add(new ModelNote.Note(i));
//        }
//        return day;
//    }
    public  List<ModelDay> MakeDay(){
        List<ModelDay> dayList = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            dayList.add(new ModelDay(i));
        }
        return dayList;
    }
//    public static List<ModelNote> makeNotes(){
//        List<ModelNote> notes = new ArrayList<>();
//        for (notes:note) {
//        }
//        return notes;
//    }

    private ModelNote getModelNotes() {
        return new ModelNote();
    }
    protected void registerPost(String route, RouteHandler handler) {
        getRoutes().put("POST " + route, handler);
    }

    private void loginPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        // преобразуем данные в формате form-urlencoded,
        // обратно в читаемый вид.
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        // отправим данные обратно пользователю,
        // что бы показать, что мы обработали запрос

    }
    private void RegisterPost(HttpExchange exchange) {
        String cType = getContentType(exchange);
        String raw = getBody(exchange);
        // преобразуем данные в формате form-urlencoded,
        // обратно в читаемый вид.
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        // отправим данные обратно пользователю,
        // что бы показать, что мы обработали запрос
//        if (isUserExist(parsed.get(MAIL))){
//            renderTemplate(exchange, "error.html", parsed);
//            return;
//        }
    }

    public static String getContentType(HttpExchange exchange) {
        return exchange.getRequestHeaders()
                .getOrDefault("Content-Type", List.of(""))
                .get(0);
    }

    protected String getBody(HttpExchange exchange) {
        InputStream input = exchange.getRequestBody();
        Charset utf8 = StandardCharsets.UTF_8;
        InputStreamReader isr = new InputStreamReader(input, utf8);
        // сейчас мы предполагаем, что клиент
        // отправляет текстовые данные
        try (BufferedReader reader = new BufferedReader(isr)) {
            return reader.lines().collect(joining(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            // путь к каталогу в котором у нас хранятся шаблоны
            // это может быть совершенно другой путь, чем тот, откуда сервер берёт файлы
            // которые отправляет пользователю
            cfg.setDirectoryForTemplateLoading(new File("data"));

            // прочие стандартные настройки о них читать тут
            // https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    protected void redirect303(HttpExchange exchange, String path) {
        try {
            exchange.getResponseHeaders().add("Location", path);
            exchange.sendResponseHeaders(303, 0);
            exchange.getResponseBody().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loginGet(HttpExchange exchange) {
        // запрос идёт на /login, но мы вернём страничку
        // из файла login.ftl
        Path path = makeFilePath("login.ftl");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void RegisterGetRequest(HttpExchange exchange) {
        Path path = makeFilePath("register.ftl");
        sendFile(exchange,path,ContentType.TEXT_HTML);
    }

    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
    try {
        // загружаем шаблон из файла по имени.
        // шаблон должен находится по пути, указанном в конфигурации
        Template temp = freemarker.getTemplate(templateFile);

        // freemarker записывает преобразованный шаблон в объект класса writer
        // а наш сервер отправляет клиенту массивы байт
        // по этому нам надо сделать "мост" между этими двумя системами

        // создаём поток который сохраняет всё, что в него будет записано в байтовый массив
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // создаём объект, который умеет писать в поток и который подходит для freemarker
        try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {

            // обрабатываем шаблон заполняя его данными из модели
            // и записываем результат в объект "записи"
            temp.process(dataModel, writer);
            writer.flush();

            // получаем байтовый поток
            var data = stream.toByteArray();

            // отправляем результат клиенту
            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
        }
    } catch (IOException | TemplateException e) {
        e.printStackTrace();
    }
}

    private void setCookie(HttpExchange exchange, Cookie response) {
        exchange.getResponseHeaders()
                .add("Set-Cookie", response.toString());
    }

    private String getCookies(HttpExchange exchange) {
        return exchange.getRequestHeaders().getOrDefault("Cookie", List.of("")).get(0);
    }

    private void sessionHandler(HttpExchange exchange) {
        Map<String, Object> data = new HashMap<>();
        String name = "times";

// получим cookie от клиента
        String cookieStr = getCookies(exchange);
        Map<String, String> cookies = Cookie.parse(cookieStr);
// если есть значение, то преобразуем в число
// если нет значения, то установим его в "0"
        String cookieValue = cookies.getOrDefault(name, "0");
        int times = Integer.parseInt(cookieValue) + 1;
// создадим cookie с новым значением
        Cookie response = Cookie.of(name, times);
// обязательно его отправим обратно на клиент
        setCookie(exchange, response);
// отрендерим страницу
        data.put(name, times);
        data.put("cookies", cookies);

        renderTemplate(exchange, "cookies.html", data);
    }
    protected String getQueryParams(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        return Objects.nonNull(query) ? query : "";
    }


}


