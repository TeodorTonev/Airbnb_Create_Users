//package com.company;
//
//public abstract class RoomRental {
//
//    public void roomRent(Room room) throws UserException {
//        User owner = room.getOwner();
//        User tanent = room.getTenant();
//        if (room != null && room.isNotRent()) {
//            owner.setBalanceMoney(owner.getBalanceMoney() + room.getPriceForMonth());
//            tanent.setBalanceMoney(tanent.getBalanceMoney() - room.getPriceForMonth());
//            room.isRent();
//        }
//        else {
//         throw new UserException("The deal did not take place");
//        }
//    }
//}
