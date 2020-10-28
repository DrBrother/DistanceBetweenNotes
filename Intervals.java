package com.company;


public class Intervals {

    public static String intervalConstruction(String[] args) {

        int semitone = 0;
        int degrees = 0;
        boolean c = true;
        String noteFinal = null;
        int semitonesSum = 0;
        String resultingNote = null;

        if (args.length < 2 || args.length > 3) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Illegal number of elements in input array");
                return e.getLocalizedMessage();
            }
        }

        switch (args[0]) {
            case "m2":
                semitone = 1;
                degrees = 2;
                break;
            case "M2":
                semitone = 2;
                degrees = 2;
                break;
            case "m3":
                semitone = 3;
                degrees = 3;
                break;
            case "M3":
                semitone = 4;
                degrees = 3;
                break;
            case "P4":
                semitone = 5;
                degrees = 4;
                break;
            case "P5":
                semitone = 7;
                degrees = 5;
                break;
            case "m6":
                semitone = 8;
                degrees = 6;
                break;
            case "M6":
                semitone = 9;
                degrees = 6;
                break;
            case "m7":
                semitone = 10;
                degrees = 7;
                break;
            case "M7":
                semitone = 11;
                degrees = 7;
                break;
            case "P8":
                semitone = 12;
                degrees = 8;
                break;
            default:
                System.out.print("No such Intervals");
                break;
        }


        String noteStart = args[1].substring(0, 1);
        if (args.length == 3) {
            if (args[2].equals("dsc")) {
                c = false;
            }
        }
        String[] degreesArray = {"C", "D", "E", "F", "G", "A", "B", "C", "D", "E", "F", "G", "A", "B", "C"};
        if (c) {
            for (int i = 0; i < degreesArray.length; i++) {
                if (degreesArray[i].equals(noteStart)) {
                    i = i + (degrees - 1);
                    noteFinal = degreesArray[i];
                    break;
                }
            }
        } else {
            for (int i = degreesArray.length - 1; i >= 0; i--) {
                if (degreesArray[i].equals(noteStart)) {
                    i = i - (degrees - 1);
                    noteFinal = degreesArray[i];
                    break;
                }
            }
        }

        String[] semitonesArray = {"C", "2", "D", "2", "E", "1", "F", "2", "G", "2", "A", "2", "B", "1",
                "C", "2", "D", "2", "E", "1", "F", "2", "G", "2", "A", "2", "B", "1",
                "C"};


        if (noteStart.equals(noteFinal)) {
            semitonesSum = 12;

        } else {
            boolean meetingFistNote = false;
            if (c) {
                for (int i = 0; i < semitonesArray.length; i++) {
                    // суммируем по пути все полутона
                    if (i % 2 != 0) {
                        semitonesSum += Integer.parseInt(semitonesArray[i]);
                    }
                    if (i % 2 == 0 && semitonesArray[i].equals(noteStart)) {
                        semitonesSum = 0;
                        meetingFistNote = true;
                        continue;
                    }
                    if (semitonesArray[i].equals(noteFinal) && meetingFistNote) {
                        break;
                    }
                }
            } else {
                for (int i = semitonesArray.length - 1; i >= 0; i--) {

                    if (i % 2 != 0) {
                        semitonesSum += Integer.parseInt(semitonesArray[i]);
                    }
                    if (semitonesArray[i].equals(noteStart)) {
                        semitonesSum = 0;
                        meetingFistNote = true;
                        continue;
                    }
                    if (semitonesArray[i].equals(noteFinal) && meetingFistNote) {
                        break;
                    }
                }
            }
        }

        if (args[1].length() == 2) {
            if (c) {
                if (args[1].charAt(1) == '#') {
                    semitonesSum--;
                } else {
                    semitonesSum++;
                }

            } else {
                if (args[1].charAt(1) == '#') {
                    semitonesSum++;
                } else {
                    semitonesSum--;
                }
            }
        }

        int semitoneDifference = semitone - semitonesSum;
        if (semitoneDifference == 0) {
            resultingNote = noteFinal;
        } else {
            if (c) {
                if (semitoneDifference == 1) {
                    resultingNote = noteFinal + "#";
                }
                if (semitoneDifference == 2) {
                    resultingNote = noteFinal + "##";
                }
                if (semitoneDifference == -1) {
                    resultingNote = noteFinal + "b";
                }
                if (semitoneDifference == -2) {
                    resultingNote = noteFinal + "bb";
                }
            } else {
                if (semitoneDifference == 1) {
                    resultingNote = noteFinal + "b";
                }
                if (semitoneDifference == 2) {
                    resultingNote = noteFinal + "bb";
                }
                if (semitoneDifference == -1) {
                    resultingNote = noteFinal + "#";
                }
                if (semitoneDifference == -2) {
                    resultingNote = noteFinal + "##";
                }
            }
        }
        System.out.println(resultingNote);
        return resultingNote;


    }

    public static String intervalIdentification(String[] args) {
        String noteStart = args[0].substring(0, 1);
        String noteFinal = args[1].substring(0, 1);
        String interval = null;
        boolean c = true;

        if (args.length == 3 && args[2].equals("dsc")) {
            c = false;
        }

        String[] semitonesArray = {"C", "2", "D", "2", "E", "1", "F", "2", "G", "2", "A", "2", "B", "1",
                "C", "2", "D", "2", "E", "1", "F", "2", "G", "2", "A", "2", "B", "1",
                "C"};

        int degrees = 0;
        int semitone = 0;

        if (noteStart.equals(noteFinal)) {
            degrees = 8;
        } else {
            boolean meetingFistNote = false;
            if (c) {
                for (int i = 0; i < semitonesArray.length; i++) {
                    if (i % 2 != 0) {
                        semitone += Integer.parseInt(semitonesArray[i]);
                    }
                    if (i % 2 == 0) {
                        degrees++;
                        if (semitonesArray[i].equals(noteStart)) {
                            degrees = 1;
                            semitone = 0;
                            meetingFistNote = true;
                        }
                        if (semitonesArray[i].equals(noteFinal) && meetingFistNote) {
                            break;
                        }
                    }
                }
            } else {
                for (int i = semitonesArray.length - 1; i >= 0; i--) {
                    if (i % 2 != 0) {
                        semitone += Integer.parseInt(semitonesArray[i]);
                    }
                    if (i % 2 == 0) {
                        degrees++;
                        if (semitonesArray[i].equals(noteStart)) {
                            degrees = 1;
                            semitone = 0;
                            meetingFistNote = true;
                        }
                        if (semitonesArray[i].equals(noteFinal) && meetingFistNote) {
                            break;
                        }
                    }
                }
            }
        }

        if (c) {
            if (args[0].length() == 2) {
                if (args[0].charAt(1) == '#') {
                    semitone--;
                } else if (args[0].charAt(1) == 'b') {
                    semitone++;
                }
            } else if (args[0].length() == 3) {
                if (args[0].substring(1).equals("##")) {
                    semitone -= 2;
                } else if (args[0].substring(1).equals("bb")) {
                    semitone += 2;
                }
            }
            if (args[1].length() == 2) {
                if (args[1].charAt(1) == '#') {
                    semitone++;
                } else if (args[1].charAt(1) == 'b') {
                    semitone--;
                }
            } else if (args[1].length() == 3) {
                if (args[1].substring(1).equals("##")) {
                    semitone += 2;
                } else if (args[1].substring(1).equals("bb")) {
                    semitone -= 2;
                }
            }
        } else {
            if (args[0].length() == 2) {
                if (args[0].charAt(1) == '#') {
                    semitone++;
                } else if (args[0].charAt(1) == 'b') {
                    semitone--;
                }
            } else if (args[0].length() == 3) {
                if (args[0].substring(1).equals("##")) {
                    semitone += 2;
                } else if (args[0].substring(1).equals("bb")) {
                    semitone -= 2;
                }
            }
            if (args[1].length() == 2) {
                if (args[1].charAt(1) == '#') {
                    semitone--;
                } else if (args[1].charAt(1) == 'b') {
                    semitone++;
                }
            } else if (args[1].length() == 3) {
                if (args[1].substring(1).equals("##")) {
                    semitone -= 2;
                } else if (args[1].substring(1).equals("bb")) {
                    semitone += 2;
                }
            }
        }

        if (semitone == 1 && degrees == 2) {
            interval = "m2";
        } else if (semitone == 2 && degrees == 2) {
            interval = "M2";
        } else if (semitone == 3 && degrees == 3) {
            interval = "m3";
        } else if (semitone == 4 && degrees == 3) {
            interval = "M3";
        } else if (semitone == 5 && degrees == 4) {
            interval = "P4";
        } else if (semitone == 7 && degrees == 5) {
            interval = "P5";
        } else if (semitone == 8 && degrees == 6) {
            interval = "m6";
        } else if (semitone == 9 && degrees == 6) {
            interval = "M6";
        } else if (semitone == 10 && degrees == 7) {
            interval = "m7";
        } else if (semitone == 11 && degrees == 7) {
            interval = "M7";
        } else if (semitone == 12 && degrees == 8) {
            interval = "P8";
        } else {
            try {
                throw new Exception("Cannot identify the interval");
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
        }

        return interval;
    }
}

