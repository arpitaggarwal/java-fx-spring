package test.controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.lang.StringUtils;
import org.jpedal.PdfDecoderFX;
import org.jpedal.examples.viewer.ViewerInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    private static String FILE = "/Users/ArpitAggarwal/Downloads/Download.pdf";
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label messageLabel;

    private Pagination pagination;
    private final Paint background = RadialGradientBuilder.create()
    .stops(new Stop(0d, Color.TURQUOISE), new Stop(1, Color.web("3A5998")))

            .centerX(0.5d).centerY(0.5d).build();

    //Class containing grid (see below)
  //  private GridDisplay gridDisplay;

    @FXML
    public void sayHello(ActionEvent event) throws Exception {
        System.setProperty("javafx.macosx.embedded", "true");
        Toolkit.getDefaultToolkit();

        final HBox root = new HBox(5);
        root.setPadding(new Insets(5));
        Desktop.getDesktop().open(new File(FILE));
//        final Button button = new Button("Browse");
//        final FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System
//                .getProperty("user.home")));
//        fileChooser.getExtensionFilters().add(
//                new ExtensionFilter("PDF Files", "*.pdf", "*.PDF"));
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                final File file = fileChooser.showOpenDialog(primaryStage);
//                if (file != null) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Desktop.getDesktop().open(file);
//                            } catch (IOException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
//                }
//            }
//        });
//
//        root.getChildren().add(button);
        String user = username.getText();
        String pass = password.getText();

        if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(pass) && user.equals(pass)) {
            //  messageLabel.setText("True :: has internet Connection" + hasActiveInternetConnection());
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // OR
            Stage stageTheLabelBelongs = (Stage) messageLabel.getScene().getWindow();
            // these two of them return the same stage
            // Swap screen
            // stageTheLabelBelongs.setScene(new Scene(new Pane()));


//            stage.setScene(scene);
//            stage.setTitle("PaginationSample");
//            stage.show();


            String fxmlFile = "/fxml/display.fxml";
            log.debug("Loading FXML for main view from: {}", fxmlFile);
            FXMLLoader loader = new FXMLLoader();
            Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

            log.debug("Showing JFX scene");
            pagination = new Pagination(10, 0);
         //   pagination.setStyle("-fx-border-color:red;");
            pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex, stageTheLabelBelongs));
            //Represents the grid with Rectangles
           /* gridDisplay = new GridDisplay(400, 200);

            //Fields to specify number of rows/columns
            TextField rowField = new TextField();
            TextField columnField = new TextField();
            //Function to set an action when text field loses focus
            buildTextFieldActions(rowField, columnField);
            HBox fields = new HBox();
            fields.getChildren().add(rowField);
            fields.getChildren().add(new Label("x"));
            fields.getChildren().add(columnField);

            BorderPane mainPanel = new BorderPane();
            mainPanel.setLeft(gridDisplay.getDisplay());
            mainPanel.setBottom(fields);*/
/*
            GridPane root = new GridPane();
            root.setPadding(new Insets(5));
            root.setHgap(2);
            root.setVgap(2);
            root.setAlignment(Pos.CENTER);
            final TextField tex = new TextField();
            tex.setPrefWidth(250);
            tex.setPrefHeight(50);
            tex.setPromptText("Press the buttons to Display text here");
            tex.setFocusTraversable(false);
            root.setColumnSpan(tex, 7);
            root.setRowSpan(tex, 2);
            root.add(tex, 0, 0);
            root.setHalignment(tex, HPos.CENTER);
            final Button[][] btn = new Button[5][5];
            char ch = 'A';
            for ( int i = 0; i < btn.length; i++) {
                for ( int j = 0; j < btn.length; j++) {

                    btn[i][j] = new Button("" + ch);
                    btn[i][j].setPrefSize(50, 50);
                    root.add(btn[i][j], j, i+2);
                    ch++;
                    btn[i][j].setOnMouseClicked(new EventHandler<MouseEvent>()

                                                {
                                                    @Override
                                                    public void handle(MouseEvent arg0) {
                                                        Button b= (Button)arg0.getSource();
                                                        tex.appendText(b.getText());
                                                    }}
                    );
                }
            }

            Scene scene = new Scene(root,background);*/

//
            AnchorPane anchor = new AnchorPane();
            AnchorPane.setTopAnchor(pagination, 10.0);
            AnchorPane.setRightAnchor(pagination, 10.0);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 10.0);
            anchor.getChildren().addAll(pagination);
            Scene scene = new Scene(anchor, 1000, 400);
            //Scene scene = new Scene(mainPanel);

        //    Scene scene = new Scene(rootNode, 1000, 400);
            scene.getStylesheets().add("/styles/styles.css");




         /*   if (hasActiveInternetConnection()) {
                stageTheLabelBelongs.setTitle("Online Mode");
            } else {
                stageTheLabelBelongs.setTitle("Offline Mode");
            }*/

            stageTheLabelBelongs.setScene(scene);

        } else {
            messageLabel.setText("False :: has internet Connection" + hasActiveInternetConnection());
        }


    }

    /**
     * http://stackoverflow.com/questions/3584210/preferred-java-way-to-ping-a-http-url-for-availability
     *
     * @return
     */

    /*public void openPdf() throws Exception{
        FileChooser fc = new FileChooser();
        fc.setTitle("Open PDF file...");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File f = fc.showOpenDialog(stage.getOwner());
        String filename = file.getAbsolutePath();

// open file.
        PdfDecoderFX pdf = new PdfDecoderFX();
        pdf.openPdfFile(filename);
        showPage(pdf, 1);
        pdf.closePdfFile();
    }*/

    /**
     * Update the GUI to show a specified page.
     * @param page
     */
    /*private void showPage(PdfDecoderFX pdf, int page) {

        //Check in range
        if (page > pdf.getPageCount())
            return;
        if (page < 1)
            return;

        //Store
        pageNumber = page;


        //Show/hide buttons as neccessary
        if (page == pdf.getPageCount())
            next.setVisible(false);
        else
            next.setVisible(true);

        if (page == 1)
            back.setVisible(false);
        else
            back.setVisible(true);


        //Calculate scale
        int pW = pdf.getPdfPageData().getCropBoxWidth(page);
        int pH = pdf.getPdfPageData().getCropBoxHeight(page);

        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();

        s.width -= 100;
        s.height -= 100;

        double xScale = (double)s.width / pW;
        double yScale = (double)s.height / pH;
        double scale = xScale < yScale ? xScale : yScale;

        //Work out target size
        pW *= scale;
        pH *= scale;

        //Get image and set
        Image i = getPageAsImage(page,pW,pH);
        imageView.setImage(i);

        //Set size of components
        imageView.setFitWidth(pW);
        imageView.setFitHeight(pH);
        stage.setWidth(imageView.getFitWidth()+2);
        stage.setHeight(imageView.getFitHeight()+2);
        stage.centerOnScreen();
    }

    *//**
     * Wrapper for usual method since JFX has no BufferedImage support.
     * @param page
     * @param width
     * @param height
     * @return
     *//*
    private Image getPageAsImage(int page, int width, int height) {

        BufferedImage img;
        try {
            img = pdf.getPageAsImage(page);

            //Use deprecated method since there's no real alternative
            //(for JavaFX 2.2+ can use SwingFXUtils instead).
            if (Image.impl_isExternalFormatSupported(BufferedImage.class))
                return javafx.scene.image.Image.impl_fromExternalImage(img);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }*/
    public boolean hasActiveInternetConnection() {
        try {
            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1500);
            urlc.connect();
            System.out.println(urlc.getResponseCode());
            return (urlc.getResponseCode() == 200);
        } catch (Exception e) {

        }
        return false;
    }


    public int itemsPerPage() {
        return 4;
    }

    public VBox createPage(int pageIndex,  Stage stageTheLabelBelongs) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            VBox element = new VBox();
           // BorderPane element = new BorderPane();
            Hyperlink link = new Hyperlink("Click to Open");
            link.setVisited(true);
            link.setId(""+i);
            link.setOnMouseClicked(new EventHandler<MouseEvent>()

                                   {
                                       @Override
                                       public void handle(MouseEvent arg0) {
                                           Hyperlink link = (Hyperlink) arg0.getSource();
                                           System.out.println("Hey  .... " + link.getId());
                                           FileView view = new FileView() {
                                               @Override
                                               public String getName(File f) {
                                                   return super.getName(new File("/Users/ArpitAggarwal/Downloads/Download.pdf"));
                                               }
                                           };

                                           /*FileChooser fileChooser = new FileChooser();
                                           final File file = fileChooser.showOpenDialog(stageTheLabelBelongs);

                                           try {Desktop.getDesktop().open(file);
                                           } catch (IOException e) {
                                               // TODO Auto-generated catch block
                                               //    e.printStackTrace();
                                           }*/
                                          /* @Override
                                               public void handle(ActionEvent event) {
                                               final File file = fileChooser.showOpenDialog(primaryStage);
                                               if (file != null) {
                                                   new Thread(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           try {
                                                               Desktop.getDesktop().open(file);
                                                           } catch (IOException e) {
                                                               // TODO Auto-generated catch block
                                                           //    e.printStackTrace();
                                                           }
                                                       }
                                                   }).start();
                                               }
                                           }*/

                                           // get file path.
                                         /*  FileChooser fc = new FileChooser();
                                           fc.setTitle("Open PDF file...");
                                           fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));*/
                                         //  File f = fc.showOpenDialog(stage.getOwner());
                                         //  File f = fc.showOpenDialog(new Window().geto);
                                       //    String filename = file.getAbsolutePath();

// open file.
//                                           PdfDecoder pdf = new PdfDecoder();
//                                           pdf.openPdfFile("");
//                                          // pdf.openPdfFile(filename);
//                                           showPage(1);
//                                           pdf.closePdfFile();

                                       }}
            );
            Label text = new Label(link.getText() + " Book " + (i + 1));

        //    text.applyCss();
            element.getChildren().addAll(link, text);
            box.getChildren().add(element);
        }
        return box;
    }

    /*private void buildTextFieldActions(final TextField rowField, final TextField columnField) {
        rowField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1) {
                    if (!rowField.getText().equals("")) {
                        try {
                            int nbRow = Integer.parseInt(rowField.getText());
                            gridDisplay.setRows(nbRow);
                            gridDisplay.updateDisplay();
                        } catch (NumberFormatException nfe) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                }
            }
        });

        columnField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (!t1) {
                    if (!columnField.getText().equals("")) {
                        try {
                            int nbColumn = Integer.parseInt(columnField.getText());
                            gridDisplay.setColumns(nbColumn);
                            gridDisplay.updateDisplay();
                        } catch (NumberFormatException nfe) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                }
            }
        });*/
   // }

    //Class responsible for displaying the grid containing the Rectangles
   /* public class GridDisplay {

        private GridPane gridPane;
        private int nbRow;
        private int nbColumn;
        private int width;
        private int height;
        private double hGap;
        private double vGap;
        private ReadOnlyDoubleProperty heightProperty;
        private ReadOnlyDoubleProperty widthProperty;

        public GridDisplay(int width, int height) {
            this.gridPane = new GridPane();
            this.width = width;
            this.height = height;
            build();
        }

        private void build() {
            this.hGap = 0.1 * width;
            this.vGap = 0.1 * height;
            gridPane.setVgap(vGap);
            gridPane.setHgap(hGap);
            gridPane.setPrefSize(width, height);
            initializeDisplay(width, height);
        }

        //Builds the first display (correctly) : adds a Rectangle for the number
        //of rows and columns
        private void initializeDisplay(int width, int height) {
            nbRow = height / 100;
            nbColumn = width / 100;

            for (int i = 0; i < nbColumn; i++) {
                for (int j = 0; j < nbRow; j++) {
                    Rectangle rectangle = new Rectangle(100, 100);
                    rectangle.setStroke(Paint.valueOf("orange"));
                    rectangle.setFill(Paint.valueOf("steelblue"));
                    gridPane.add(rectangle, i, j);

                }
            }
        }

        //Function detailed in post
        //Called in updateDisplay()
        public void refreshConstraints() {
            gridPane.getRowConstraints().clear();
            gridPane.getColumnConstraints().clear();
            for (int i = 0; i < nbRow; i++) {
                RowConstraints rConstraint = new RowConstraints();
                rConstraint.setPercentHeight(100 / nbRow - ((nbRow - 1) * 10 / nbRow));
                gridPane.getRowConstraints().add(rConstraint);
            }

            for (int i = 0; i < nbColumn; i++) {
                ColumnConstraints cConstraint = new ColumnConstraints();
                cConstraint.setPercentWidth(100 / nbColumn - ((nbColumn - 1) * 10 / nbColumn));
                gridPane.getColumnConstraints().add(cConstraint);
            }

        }

        public void setColumns(int newColumns) {
            nbColumn = newColumns;
        }

        public void setRows(int newRows) {
            nbRow = newRows;
        }

        public GridPane getDisplay() {
            return gridPane;
        }

        //Function called when refreshing the display
        public void updateDisplay() {
            gridPane.getChildren().clear();
            for (int i = 0; i < nbColumn; i++) {
                for (int j = 0; j < nbRow; j++) {
                    Rectangle rectangle = new Rectangle(100, 100);
                    //Binding the fraction of the grid size to the width
                    //and heightProperty of the child
                    rectangle.widthProperty().bind(widthProperty.divide(nbColumn));
                    rectangle.heightProperty().bind(heightProperty.divide(nbRow));
                    gridPane.add(rectangle, i, j);
                }
            }
        }

    }*/
  /*  private void gridPane(){
        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid");

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS.ALWAYS);
        grid.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setFillHeight(true);
        rowConstraints.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().add(new RowConstraints());
        grid.getRowConstraints().add(new RowConstraints());
        grid.getRowConstraints().add(rowConstraints);

        String defaultQlsLocation = "";//DEFAULT_QLS_INPUT_FILE_DIRECTORY + DEFAULT_QLS_INPUT_FILE_NAME;
        String defaultQlLocation = "";//DEFAULT_QL_INPUT_FILE_DIRECTORY + DEFAULT_QL_INPUT_FILE_NAME;
        final TextField qlInputFileTextField = new TextField(defaultQlLocation);
        final TextField qlsInputFileTextField = new TextField(defaultQlsLocation);
        Button qlChooseInputButton = new Button("Choose ql file...");
        Button qlsChooseInputButton = new Button("Choose qls file...");
        Button parseButton = new Button("Parse");
        Button showButton = new Button("Show");
        grid.add(qlInputFileTextField, 0, 0);
        grid.add(qlChooseInputButton, 1, 0);
        grid.add(qlsInputFileTextField, 0, 1);
        grid.add(qlsChooseInputButton, 1, 1);
        grid.add(parseButton, 2, 1);
        grid.add(showButton, 3, 1);
        showButton.setVisible(false);

        PathSelectedCallback qlPathSelectedCallback = path -> qlInputFileTextField.setText(path);
        PathSelectedCallback qlsPathSelectedCallback = path -> qlsInputFileTextField.setText(path);
        qlChooseInputButton.setOnAction(new ChooseInputButtonHandler(qlPathSelectedCallback, defaultQlLocation));
        qlsChooseInputButton.setOnAction(new ChooseInputButtonHandler(qlsPathSelectedCallback, defaultQlsLocation));

        StackPane stackPane = new StackPane();
        ValidationsGridPane validationsGridPane = new ValidationsGridPane();

        InputFileTextCallback inputQLFileTextCallback = () -> qlInputFileTextField.getText();
        InputFileTextCallback inputQLSFileTextCallback = () -> qlsInputFileTextField.getText();
        ParseResultCallback parseCombinedResultCallback = parsingResult -> {
            CombinedParsingResult combinedParsingResult = (CombinedParsingResult) parsingResult;
            QuestionnaireParsingResult questionnaireParsingResult = combinedParsingResult.getQuestionnaireParsingResult();
            showNode(stackPane, validationsGridPane);
            ValidationResult validationResult = combinedParsingResult.validate();
            showButton.setVisible(!validationResult.containsErrors());
            validationsGridPane.showValidations(validationResult.getValidationMessages());
            questionnaire = questionnaireParsingResult.getQuestionnaire();
            StylesheetParsingResult stylesheetParsingResult = combinedParsingResult.getStylesheetParsingResult();
            stylesheet = stylesheetParsingResult.getStylesheet();
        };
        parseButton.setOnAction(new QLSParseButtonHandler(inputQLFileTextCallback, inputQLSFileTextCallback,
                parseCombinedResultCallback));

        showButton.setOnAction(event -> {
            QuestionnaireToRuntimeQuestions questionnaireToRuntimeQuestions = new QuestionnaireToRuntimeQuestions();
            List<RuntimeQuestion> runtimeQuestions = questionnaireToRuntimeQuestions.createRuntimeQuestions(questionnaire);

            List<Page> pages = stylesheet.getPages();
            Pagination pagination = new Pagination(pages.size());
            pagination.setPageFactory(pageIndex -> {
                Page page = pages.get(pageIndex);
                List<RuntimeQuestion> questionsOnThisPage = runtimeQuestions.stream()
                        .filter(rq -> page.containsQuestion(rq.getQuestion().getName())).collect(Collectors.toList());
                QuestionnaireGridPane questionnaireGridPane = new StyledQuestionnaireGridPane(page);
                questionnaireGridPane.showQuestions(runtimeQuestions, questionsOnThisPage);
                return new ScrollPane(questionnaireGridPane);
            });
            AnchorPane anchor = new AnchorPane();
            AnchorPane.setTopAnchor(pagination, 10.0);
            AnchorPane.setRightAnchor(pagination, 10.0);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 10.0);
            anchor.getChildren().addAll(pagination);
            showNode(stackPane, anchor);
        });

        grid.add(stackPane, 0, 2, 4, 1);

        Scene scene = new Scene(grid, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("UIElements.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/
}
