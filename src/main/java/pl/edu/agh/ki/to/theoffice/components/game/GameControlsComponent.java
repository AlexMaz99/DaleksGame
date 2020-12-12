package pl.edu.agh.ki.to.theoffice.components.game;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.edu.agh.ki.to.theoffice.common.component.FXMLUtils;
import pl.edu.agh.ki.to.theoffice.domain.map.Location.Direction;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static pl.edu.agh.ki.to.theoffice.common.component.ImageUtils.setSquareSize;
import static pl.edu.agh.ki.to.theoffice.domain.map.Location.Direction.*;

@Slf4j
public class GameControlsComponent extends TilePane implements FXMLComponent {

    private static final int arrowSize = 45;

    private List<ImageView> controlArrows;

    public GameControlsComponent() {
        FXMLUtils.loadFXML(this);
    }

    @Override
    public String getFxmlResourceFile() {
        return "/view/game/game-controls.fxml";
    }

    public void initArrows() {

        this.controlArrows = this.getChildren().stream()
                .map(node -> (TilePane) node)
                .map(Pane::getChildren)
                .flatMap(Collection::stream)
                .map(node -> (ImageView) node)
                .peek(control -> setSquareSize(control, arrowSize))
                .collect(Collectors.toList());
    }

    public void setArrowListeners(ArrowClicked lambda) {
        controlArrows.stream()
                .forEach(action -> action.setOnMouseClicked(mouseEvent -> {
                    Direction direction = getAngleDependentOnDirection((int) action.getRotate());
                    lambda.arrowClicked(direction);
                }));
    }

    public void removeArrowListeners() {
        controlArrows.forEach(action -> action.setOnMouseClicked(null));
    }

    private Direction getAngleDependentOnDirection(int rotation) {
        log.debug("rotation: {}", rotation);
        return switch (rotation) {
            case 225 -> NORTH_WEST;
            case 270 -> NORTH;
            case 315 -> NORTH_EAST;
            case 180 -> WEST;
            case 0 -> EAST;
            case 135 -> SOUTH_WEST;
            case 90 -> SOUTH;
            case 45 -> SOUTH_EAST;
            case -1 -> NONE;
            default -> throw new IllegalStateException("Unexpected value: " + rotation);
        };
    }

    @FunctionalInterface
    public interface ArrowClicked {
        void arrowClicked(Direction direction);
    }

    @FunctionalInterface
    public interface ControlClicked {
        void mouseClicked(MouseEvent event);

    }
}
