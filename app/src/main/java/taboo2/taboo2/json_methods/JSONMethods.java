package taboo2.taboo2.json_methods;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import taboo2.taboo2.global.Global;


public class JSONMethods {

    public JSONMethods(Context context) {
        this.context = context;
        createFilesArray();
        init();
    }

    private void init() {
        random = new Random();
        list_keysToForbiddenWords = new ArrayList<>();
        if (array_AllWordsToGuess == null) {
            indexes_usedWordsToGuess = new ArrayList<>();
        }
    }

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private static Context context;
    private String string_wordToGuess;
    private JSONObject wholeJSON;
    private JSONObject array_wordToGuess;
    private Random random;
    private static InputStream inputStream;
    private String fileName;
    private static List<String> files;
    private String arrayName;
    private static JSONArray array_AllWordsToGuess;
    private static List<Integer> indexes_usedWordsToGuess;
    private List<String> list_keysToForbiddenWords;
    private List<String> list_notRequiredWords;
    private List<String> list_keysToWordsToTextViews;
    private List<String> list_wordsToTextViews;
    private JSONObject json_ForbiddenWords = null;

    public void createJSONObject() {
        try {
            wholeJSON = new JSONObject(convertJSONFileToObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void createArrayWithAllWordsToGuess() {
        try {
            array_AllWordsToGuess = wholeJSON.getJSONArray(arrayName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* ==========================================
    ------------------AAAAAAAAA------------------
    ========================================== */

    private void createFilesArray() {
        files = new ArrayList<>();
        if (Global.isEasyLevelChecked()) {
            files.add("Taboo_easy.json");
        }
        if (Global.isAverageLevelChecked()) {
            files.add("Taboo_average.json");
        }
        if (Global.isDifficultLevelChecked()) {
            files.add("Taboo_difficult.json");
        }
        if (Global.isVeryDifficultLevelChecked()) {
            files.add("Taboo_veryDifficult.json");
        }
    }

//    private void createFilesArray() {
//        String content = getJsonFromAssetFile(context, "Taboo_difficult.json");
//        JSONObject finalJson = new JSONObject();
//        File file = new File("T.json");
//        try {
//            JSONObject jsonObject = new JSONObject(content);
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.put(jsonObject);
//            finalJson.put("T.json", jsonArray);
//        } catch(JSONException e) {
//            e.printStackTrace();
//        }
//        try {
//            writeFile(finalJson.toString().getBytes(), file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void writeFile(byte[] data, File file) throws IOException{
//        BufferedOutputStream bos = null;
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bos = new BufferedOutputStream(fos);
//            bos.write(data);
//        }
//        finally {
//            if (bos != null) {
//                try {
//                    bos.flush();
//                    bos.close();
//                } catch (Exception e) {
//
//                }
//            }
//        }
//    }

    public static String getJsonFromAssetFile(Context context, String jsonFileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /* ==========================================
    ------------------AAAAAAAAA------------------
    ========================================== */

    public void getRandomFile() {
        int random = (int) (Math.random() * files.size());
        fileName = files.get(random);
    }

    public void createCorrectArray() {
        if (fileName.equals("Taboo_easy.json")) {
            arrayName = "Taboo_easy";
        }
        if (fileName.equals("Taboo_average.json")) {
            arrayName = "Taboo_average";
        }
        if (fileName.equals("Taboo_difficult.json")) {
            arrayName = "Taboo_difficult";
        }
        if (fileName.equals("Taboo_veryDifficult.json")) {
            arrayName = "Taboo_veryDifficult";
        }
    }

    public void createInputStream() {
        try {
            inputStream = context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initWordToGuess() {
        int index_wordToGuess;
        int numOfWordsToGuess = array_AllWordsToGuess.length();
        do {
            index_wordToGuess = random.nextInt(numOfWordsToGuess);
        } while (checkIfWordWasAlreadySearched(index_wordToGuess));
        indexes_usedWordsToGuess.add(index_wordToGuess);
        try {
            array_wordToGuess = array_AllWordsToGuess.getJSONObject(index_wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        string_wordToGuess = array_wordToGuess.keys().next();
    }

    private String convertJSONFileToObject() {
        String json;
        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void createJSONWithForbiddenWords() {
        try {
            json_ForbiddenWords = array_wordToGuess.getJSONObject(string_wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createListWithKeysToForbiddenWords() {
        Iterator<?> keys = json_ForbiddenWords.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            list_keysToForbiddenWords.add(key);
        }
    }

    public void addRequiredWords() {
        list_notRequiredWords = new ArrayList<>();
        list_keysToWordsToTextViews = new ArrayList<>();
        for (String key : list_keysToForbiddenWords) {
            if (key.contains("REQ")) {
                list_keysToWordsToTextViews.add(key);
            } else {
                list_notRequiredWords.add(key);
            }
        }
    }

    public void addRestOfWords(TextView[] forbiddenWordstextViews) {
        int numOfTextViews = forbiddenWordstextViews.length;
        int numOfTextViewsLeft = numOfTextViews - list_keysToWordsToTextViews.size();
        for (int i = 0; i < numOfTextViewsLeft; i++) {
            int number;
            do {
                number = (int) (Math.random() * list_notRequiredWords.size());
            } while (list_keysToWordsToTextViews.contains(list_notRequiredWords.get(number)));
            list_keysToWordsToTextViews.add(list_notRequiredWords.get(number));
        }
    }

    public void modifyKeysToForbiddenWords() {
        list_wordsToTextViews = new ArrayList<>();
        String forbiddenWord = null;
        for (String key : list_keysToWordsToTextViews) {
            try {
                forbiddenWord = json_ForbiddenWords.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list_wordsToTextViews.add(forbiddenWord);
        }
    }


    public void addForbiddenWordsToFields(TextView[] textViews) {
        List<Integer> indexesAlreadyUsed = new ArrayList<>();
        for (TextView textView : textViews) {
            int number;
            do {
                number = (int) (Math.random() * textViews.length);
            } while (indexesAlreadyUsed.contains(number));
            indexesAlreadyUsed.add(number);
            String forbiddenWord = list_wordsToTextViews.get(number);
            textView.setText(forbiddenWord);
        }
    }

    public void addWordToGuessToField(TextView wordToGuessTextView) {
        wordToGuessTextView.setText(string_wordToGuess);
    }

    public boolean checkIfWordWasAlreadySearched(int randomNumber) {
        System.out.println(indexes_usedWordsToGuess.size());
        System.out.println(list_keysToForbiddenWords.size());
        if (indexes_usedWordsToGuess.size() == array_AllWordsToGuess.length()) {
            Toast.makeText(context, "Hasła zostały zresetowane", Toast.LENGTH_LONG).show();
            indexes_usedWordsToGuess.clear();
        }

        if (indexes_usedWordsToGuess.contains(randomNumber)) {
            return true;
        }
        return false;

        /*
        Dodać opcję, że jeśli wykorzysta się wszystkie słowa to
        pojawi się informacja że słowa zostały zresetowane
        i będą wyświetlane od nowa
         */
    }

    /* ==========================================
    -------------------GETTERS-------------------
    ========================================== */

    public List<String> getList_wordsToTextViews() {
        return list_wordsToTextViews;
    }

    public List<String> getList_keysToForbiddenWords() {
        return list_keysToForbiddenWords;
    }
}
