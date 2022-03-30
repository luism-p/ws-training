package com.liferay.training.gradebook.web;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author luism on 31/01/2021
 * @project ws-training
 */
class UtilTest {

    @Mock
    private JSONObject jsonObject;

    @Mock
    private DLFileEntry dlFileEntry;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private File file;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private DLFileEntryLocalService dlFileEntryLocalService;

    /**
     * <strong>@beforeAll</strong>
     * <p>Función que se ejecuta en el inicio de los test, es el primer método que se ejecuta.
     * No es frecuente utilizarlo, pero un uso común es para abrir conexiones externas como BBDD o
     * inicializar variables globales</p>
     * </p>
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll: Inicio tests");
    }

    /**
     * <strong>@AfterAll</strong>
     * <p>Función que se ejecuta cuando terminan los test, es el último método que se ejecuta.
     * No es frecuente utilizarlo, pero un uso común es para cerrar conexiones externas como BBDD</p>
     */
    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll: fin tests");
    }

    /**
     * <strong>@BeforeEach</strong>
     * <p>Función que se ejecuta antes de cada test</p>
     */
    @BeforeEach
    public void setUp() {
        System.out.println("BeforeEach: Inicio test");
        //IMPORTANT: Inicia los mocks declarados mediante anotación.
        MockitoAnnotations.openMocks(this);
    }

    /**
     * <strong>@AfterEach</strong>
     * <p>Función que se ejecuta después de cada test</p>
     */
    @AfterEach
    void tearDown() {
        System.out.println("AfterEach: Fin test");
    }

    /**
     * <strong>@ParameterizedTest</strong>
     * <p>Las pruebas parametrizadas permiten ejecutar una prueba con diferentes argumentos.
     * Debemos proporcionar un origen como vemos a continuación.
     * Podemos proporcionar un name para mostrarlo en la lista de pruebas. Puede contener el valor del argumento ejecutado.
     * </p>
     * <br/>
     * <strong>@ValueSource</strong>
     * <p>Es una de las fuentes más simples.
     * Permite especificar una sola matriz de valores literales.
     * Debemos indicar el tipo de literal.
     * Literales posibles: short, byte, int, long, float, double, char, boolean, java.lang.String, java.lang.Class
     * Tiene una limitación de 1 argumento por ejecución.
     * </p>
     * <br/>
     *
     * @param argument int
     */
    @ParameterizedTest(name = "Test getString int argument {0}")
    @ValueSource(ints = { 1, 2, 3 })
    void valueSource_getStringTest(int argument) {
        String result = Util.getString(argument);
        assertNotNull(result);
        assertEquals(String.valueOf(argument), result);
    }

    /**
     * <strong>@ParameterizedTest</strong>
     * <p>Las pruebas parametrizadas permiten ejecutar una prueba con diferentes argumentos.
     * Debemos proporcionar un origen como vemos a continuación.
     * Podemos proporcionar un name para mostrarlo en la lista de pruebas. Puede contener el valor del argumento ejecutado.
     * </p>
     * <br/>
     * <strong>@ValueSource</strong>
     * <p>Es una de las fuentes más simples.
     * Permite especificar una sola matriz de valores literales.
     * Debemos indicar el tipo de literal.
     * Literales posibles: short, byte, int, long, float, double, char, boolean, java.lang.String, java.lang.Class
     * Tiene una limitación de 1 argumento por ejecución.
     * </p>
     * <br/>
     *
     * @param argument boolean
     */
    @ParameterizedTest(name = "Test boolean getString boolean argument {0}")
    @ValueSource(booleans = { true, false })
    void valueSource_getStringTest(boolean argument) {
        String result = Util.getString(argument);
        assertAll(() -> {
            assertNotNull(result);
            assertEquals(String.valueOf(argument), result);
        });

    }

    /**
     * <strong>@ParameterizedTest</strong>
     * <p>Las pruebas parametrizadas permiten ejecutar una prueba con diferentes argumentos.
     * Debemos proporcionar un origen como vemos a continuación.
     * Podemos proporcionar un name para mostrarlo en la lista de pruebas. Puede contener el valor del argumento ejecutado.
     * </p>
     * <br/>
     * <strong>@ValueSource</strong>
     * <p>Es una de las fuentes más simples.
     * Permite especificar una sola matriz de valores literales.
     * Debemos indicar el tipo de literal.
     * Literales posibles: short, byte, int, long, float, double, char, boolean, java.lang.String, java.lang.Class.
     * Tiene una limitación de 1 argumento por ejecución.
     * </p>
     * <br/>
     * <strong>@NullSource</strong>
     * <p>Añade en la iteración un valor nulo</p>
     * <br/>
     * <strong>@EmptySource</strong>
     * <p>Añade en la iteración un valor vacío. Sólo debemos añadirlo en los casos que sea posible un valor vacío.,
     * En caso de ponerlo sin ser posible nos devolverá una excepción: <i>cannot provide an empty argument to method</i>.</p>
     * <br/>
     * <strong>@NullAndEmptySource</strong>
     * <p>Añade en la iteración un valor nulo y un valor vacío.</p>
     * <br/
     *
     * @param argument String
     */
    @ParameterizedTest(name = "Test String getString String argument {0}")
    @NullSource
    @EmptySource
    //@NullAndEmptySource
    @ValueSource(strings = { "test", "test2" })
    void valueSource_getStringTest(String argument) {
        String result = Util.getString(argument);
        assertNotNull(result);
        assertEquals(String.valueOf(argument), result);
    }

    /**
     * <strong>@ParameterizedTest</strong>
     * <p>Las pruebas parametrizadas permiten ejecutar una prueba con diferentes argumentos.
     * Debemos proporcionar un origen como vemos a continuación.
     * Podemos proporcionar un name para mostrarlo en la lista de pruebas. Puede contener el valor del argumento ejecutado.
     * </p>
     * <br/>
     * <strong>@MethodSource</strong>
     * <p>Los valores que proporciona provienen de un método.
     * Si no indicamos el nombre del método buscará uno que se llame exactamente igual al propio test.
     * No tiene limitación de argumentos pero siempre que sea mas de 1 argumento deben estar como <code>Arguments.of(arg1, arg2, ...)</code>
     * </p>
     * <br/>
     *
     * @param argument int
     */
    @ParameterizedTest(name = "Test getString argument {0}")
    @MethodSource
    void methodSource_getStringTest(int argument) {
        String result = Util.getString(argument);
        assertNotNull(result);
        assertEquals(String.valueOf(argument), result);
    }

    /**
     * Proveedor que devuelve una lista de valores para el test.
     */
    static IntStream methodSource_getStringTest() {
        return IntStream.range(0, 100);
    }

    //@formatter:off
    /**
     Proveedor que devuelve una lista de valores para el test.
     */
    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of("1000", "1.000,00 \u20ac"),
                Arguments.of("100", "100,00 \u20ac"),
                Arguments.of("", ""),
                Arguments.of(null, ""),
                Arguments.of("100.0.", "100.0."),
                Arguments.of("100,0,", "100,0,"),
                Arguments.of("100.0,00", "100.0,00"),
                Arguments.of("100,0.00", "100,0.00"),
                Arguments.of("100.0.0", "100.0.0"),
                Arguments.of("100,0,0", "100,0,0"));
    }
    //@formatter:on

    /**
     * <strong>@ParameterizedTest</strong>
     * <p>Las pruebas parametrizadas permiten ejecutar una prueba con diferentes argumentos.
     * Debemos proporcionar un origen como vemos a continuación.
     * Podemos proporcionar un name para mostrarlo en la lista de pruebas. Puede contener el valor del argumento ejecutado.
     * </p>
     * <br/>
     * <strong>@MethodSource</strong>
     * <p>Los valores que proporciona provienen de un método.
     * Si no indicamos el nombre del método buscará uno que se llame exactamente igual al propio test.
     * No tiene limitación de argumentos pero siempre que sea mas de 1 argumento deben estar como <code>Arguments.of(arg1, arg2, ...)</code>
     * </p>
     * <br/>
     *
     * @param argument String
     * @param expected String
     */
    @ParameterizedTest(name = "Test formatDoubleToCurrencySpainTest argument {0}, expected {1}")
    @MethodSource("provideParameters")
    void methodSource_formatDoubleToCurrencySpainTest(String argument, String expected) {
        String result = Util.formatDoubleToCurrencySpain(argument);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * <strong>@ParameterizedTest</strong>
     * <p>Las pruebas parametrizadas permiten ejecutar una prueba con diferentes argumentos.
     * Debemos proporcionar un origen como vemos a continuación.
     * Podemos proporcionar un name para mostrarlo en la lista de pruebas. Puede contener el valor del argumento ejecutado.
     * </p>
     * <br/>
     * <strong>@CsvSource</strong>
     * <p>Valores separados por un delimitador, por defecto es coma (,). Podemos cambiarlo si especificamos uno con <code>delimiter</code>.
     * Para un string vacío, ponemos <code>''</code>
     * Para un null es suficiente con un espacio en blanco. o indicar como vamos a llamar los valores nulos con <code>nullValues</code>.
     * No tiene limitación de argumentos, son casteados automáticamente y el mismo orden de entrada (Cuidado con los tipos de datos).
     * </p>
     * <br/>
     *
     * @param argument String
     * @param expected String
     */
    @ParameterizedTest(name = "csvSource_formatDoubleToCurrencySpainTest2 argument {0}, expected {1}")
    @CsvSource(value = { "1000,00|1.000,00 \u20ac", "100,00|100,00 \u20ac", "nulo|''" }, delimiter = '|', nullValues = "nulo")
    void csvSource_formatDoubleToCurrencySpainTest2(String argument, String expected) {
        String result = Util.formatDoubleToCurrencySpain(argument);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * <strong>@RepeatedTest</strong>
     * <p>Sirva para invocar una prueba N veces.</p>
     * <br/>
     * <strong>@Disabled</strong>
     * <p>Evita que este método o clases sea ejecutada.
     * Podemos añadir un comentario.</p>
     * <br/>
     */
    @RepeatedTest(2)
    @Disabled("Deshabilitado tras bug #404")
    //    @DisabledOnJre(JAVA_9)
    //    @DisabledForJreRange(min = JAVA_9, max = JAVA_11)
    //    @EnabledOnJre({ JAVA_9, JAVA_10 })
    //    @EnabledForJreRange(min = JAVA_9, max = JAVA_11)
    void RepeatedTest_getStringTest() {
        int argument = (int) (Math.random() * 100);
        String result = Util.getString(argument);
        assertNull(result); //Debería producir un error.
        assertEquals(String.valueOf(argument), result);
    }

    /**
     * <strong>@Test</strong>
     * <p>Es la anotación mas simple para indicar un test.</p>
     * <br/>
     * <strong>@DisplayName</strong>
     * <p>Indica el nombre con el que se mostrará el test en la ejecución.</p>
     * <br/>
     */
    @Test
    @DisplayName("Test getFileFromJsonFile con null")
    public void null_jso_getFileFromJsonFile() {
        File result = Util.getFileFromJsonFile(null, dlFileEntryLocalService);
        assertNull(result);
    }

    @Test
    public void null_DLFileEntry_getFileFromJsonFile() {
        Mockito.doReturn("100").when(jsonObject).getString(Mockito.anyString(), Mockito.anyString());
        Mockito.doReturn(null).when(dlFileEntryLocalService).fetchDLFileEntryByUuidAndGroupId(Mockito.anyString(), Mockito.anyLong());
        File result = Util.getFileFromJsonFile(jsonObject, dlFileEntryLocalService);
        assertNull(result);
    }

    @Test
    public void getFileFromJsonFile() throws PortalException {
        //@formatter:off
        // IMPORTANT: mockConstruction
        try (MockedConstruction<FileOutputStream> fileOutputStream = Mockito.mockConstructionWithAnswer(FileOutputStream.class, Answers.RETURNS_MOCKS);
                MockedStatic<File> fileMockedStatic = Mockito.mockStatic(File.class, i -> file); //
                MockedStatic<IOUtils> ioUtilsMockedStatic = Mockito.mockStatic(IOUtils.class, i -> null)) {
            //@formatter:on
            //IMPORTANT: Mock in Line
            InputStream inputStream = Mockito.mock(InputStream.class);
            Mockito.doReturn("100").when(jsonObject).getString(Mockito.anyString(), Mockito.anyString());
            Mockito.doReturn(dlFileEntry).when(dlFileEntryLocalService).fetchDLFileEntryByUuidAndGroupId(Mockito.anyString(), Mockito.anyLong());
            Mockito.doReturn("test").when(dlFileEntry).getFileName();
            Mockito.doReturn("test").when(dlFileEntry).getExtension();
            Mockito.doReturn("test").when(dlFileEntry).getVersion();
            Mockito.doReturn(100L).when(dlFileEntry).getFileEntryId();
            Mockito.doReturn(inputStream).when(dlFileEntryLocalService).getFileAsStream(Mockito.anyLong(), Mockito.anyString());
            File result = Util.getFileFromJsonFile(jsonObject, dlFileEntryLocalService);
            assertNotNull(result);
        }
    }

    @Test
    public void catch_getFileFromJsonFile() throws PortalException {

        Mockito.doReturn("100").when(jsonObject).getString(Mockito.anyString(), Mockito.anyString());
        Mockito.doReturn(dlFileEntry).when(dlFileEntryLocalService).fetchDLFileEntryByUuidAndGroupId(Mockito.anyString(), Mockito.anyLong());
        Mockito.doReturn("test").when(dlFileEntry).getFileName();
        Mockito.doReturn("test").when(dlFileEntry).getExtension();
        Mockito.doReturn("test").when(dlFileEntry).getVersion();
        Mockito.doReturn(100L).when(dlFileEntry).getFileEntryId();
        //IMPORTANT: devuelve throw ()
        Mockito.doThrow(new PortalException()).when(dlFileEntryLocalService).getFileAsStream(Mockito.anyLong(), Mockito.anyString());
        //        Mockito.when(dlFileEntryLocalService.getFileAsStream(Mockito.anyLong(), Mockito.anyString())).thenThrow(new PortalException());
        File result = Util.getFileFromJsonFile(jsonObject, dlFileEntryLocalService);
        assertNotNull(result);
    }

}
