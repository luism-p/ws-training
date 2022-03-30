package com.liferay.training.gradebook.web;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.function.Function;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 * @author luism on 31/01/2021
 * @project ws-training
 */
public class Util {

    public static String getNum(JSONArray jsonArray){
        switch (getjson(jsonArray).getString("num", StringPool.BLANK)){
            case "1":
                return "1";
            case "2":
                return "2";
            default:
                return StringPool.BLANK;

        }

    }

    public static<T> String getString(T num){
        return String.valueOf(num);
    }

    public static JSONObject getjson(JSONArray jsonArray){
        return jsonArray.getJSONObject(0);
    }

    public static JSONObject createJson(){
        return JSONFactoryUtil.createJSONObject();
    }

    public static <T, R> R parseSafeValueGeneric(Function<T, R> function, T valueToParse, R defaultValue) {
        R result = null;
        if (function != null && Validator.isNotNull(valueToParse)) {
            try {
                result = function.apply(valueToParse);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result != null ? result : defaultValue;
    }

    public static String formatDoubleToCurrencySpain(String value) {
        String valueNotNull = value != null ? value : StringPool.BLANK;

        String valueWithOutComma = valueNotNull.replaceAll(StringPool.COMMA, StringPool.PERIOD);
        double valueDouble = parseSafeValueGeneric(Double::parseDouble, valueWithOutComma, Double.NaN);

        if (!Double.isNaN(valueDouble)) {
            return NumberFormat.getCurrencyInstance(LocaleUtil.SPAIN).format(valueDouble);
        }

        return valueNotNull;
    }

    /**
     * Devuelve un file a partir de un jsonFile de liferay
     *
     * @param jsonFile                jsonfile liferay
     * @param dlFileEntryLocalService servicio file entry
     * @return java.io.File
     */
    public static File getFileFromJsonFile(JSONObject jsonFile, DLFileEntryLocalService dlFileEntryLocalService) {

        String groupId = "0";
        String uuid = StringPool.BLANK;
        File file = null;

        if (Validator.isNotNull(jsonFile)) {
            groupId = jsonFile.getString("groupId", StringPool.BLANK);
            uuid = jsonFile.getString("uuid", StringPool.BLANK);
        }

        if (Validator.isNumber(groupId) && Validator.isNotNull(uuid)) {

            DLFileEntry dlFileEntry = dlFileEntryLocalService.fetchDLFileEntryByUuidAndGroupId(uuid, Long.parseLong(groupId));

            if (Validator.isNotNull(dlFileEntry)) {
                try {
                    String filename = dlFileEntry.getFileName();
                    filename = filename.contains(StringPool.PERIOD) ? filename.substring(0, filename.indexOf(StringPool.PERIOD)) : filename;
                    filename = FilenameUtils.getName(filename);

                    file = File.createTempFile(filename, StringPool.PERIOD + dlFileEntry.getExtension()); // NOSONAR
                    boolean isOk = file.setWritable(Boolean.TRUE);
                    isOk &= file.setReadable(Boolean.TRUE);

                    if (isOk) {
                        System.out.println("File is writable and readable.");
                    }
                    try (InputStream inputStream = dlFileEntryLocalService.getFileAsStream(dlFileEntry.getFileEntryId(), dlFileEntry.getVersion()); //
                            OutputStream outputStream = new FileOutputStream(file);) {
                        IOUtils.copy(inputStream, outputStream);
                    }

                }
                catch (IOException | PortalException e) {
                    e.printStackTrace();
                }
            }
        }

        return file;
    }
}
