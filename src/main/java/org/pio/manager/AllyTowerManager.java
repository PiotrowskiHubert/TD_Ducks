package org.pio.manager;


public class AllyTowerManager {

//    private List<oldFirstTowerOldOld> firstTowersList;
//    private List<oldSecondTowerOldOld> secondTowerList;
//    private List<oldThirdTowerOld> thirdTowerList;
//    private List<oldFourthTowerOldOld> fourthTowerList;
//    private List<oldFifthTowerOldOld> fifthTowerList;
//    private static List<oldAllyTower> oldAllyTowersList;
//    public static List<oldAllyTower> oldAllyTowersPlaced;
//    private BufferedImage spriteAllyTowerAtlas;


    // ----------- INPUTS ----------- //
//    public void mouseMoved(int x, int y) {
//
//        for (Ally ally : allyPlacedTowers) {
//            if (ally.mouseOver) {
//                ally.mouseOver=false;
//            }
//        }

//        // ----------------------------------------------------- //

//        for (Ally ally : allyPlacedTowers) {
//
//            if (ally.bounds.contains(x,y)){
//                ally.mouseOver=true;
//            }
//
//            if (ally.getSelected()){
//                ally.getSidePanelUpgrade().mouseMoved(x,y);
//            }
//
//        }
//
//    }
//    public void leftMouseClicked(int x, int y) {

//        if (Helper.isAllyTowerListEmpty(oldAllyTowersPlaced)){
//            return;
//        }
//
//        for (oldAllyTower oldAllyTower : oldAllyTowersPlaced) {
//            if(oldAllyTower.getSelected()){
//                oldAllyTower.getSidePanelUpgrade().mouseClicked(x,y);
//            }
//        }
//
//
//
//        for (Iterator<oldAllyTower> allyTowerPlacedIterator = oldAllyTowersPlaced.iterator(); allyTowerPlacedIterator.hasNext();){
//            oldAllyTower nextAlly = allyTowerPlacedIterator.next();
//
//            if(nextAlly.getEntityBounds().contains(x,y)){
//                nextAlly.setSelected(true);
//            }else {
//                if (!nextAlly.getSidePanelUpgrade().getSidePanelBounds().contains(x,y)){
//                    nextAlly.setSelected(false);
//                    nextAlly.setMousePressed(false);
//                }
//
//            }
//
//        }

//    }
//    public void rightMouseClicked(int x, int y) {
//
//        if (Helper.isAllyTowerListEmpty(oldAllyTowersPlaced)){
//            return;
//        }

  //  }
//    public void mousePressed(int x, int y) {
//
//        if (allyPlacedTowers==null){
//            return;
//        }
//
//        for (Ally ally : allyPlacedTowers) {
//
//            if (ally.bounds.contains(x,y)){
//                ally.mousePressed=true;
//            }
//        }

//        for (oldAllyTower oldAllyTower : oldAllyTowersPlaced) {
//
//            if (oldAllyTower.getSelected()){
//                oldAllyTower.getSidePanelUpgrade().mousePressed(x,y);
//                return;
//            }
//        }

//    }
//
//    public void mouseReleased(int x, int y) {
//
//        if (Helper.isAllyTowerListEmpty(oldAllyTowersPlaced)){
//            return;
//        }
//
//        for (oldAllyTower oldAllyTower : oldAllyTowersPlaced) {
//
//            if (oldAllyTower.getEntityBounds().contains(x,y)){
//                oldAllyTower.setMousePressed(false);
//            }
//        }

 //   }

}
