class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int n = numberOfUsers;
        int[] mentions = new int[n];
        int[] offlineTime = new int[n];
        
        // first sort the events List based on timestamp & event type
        Collections.sort(events, (e1, e2) -> {
            int t1 = Integer.parseInt(e1.get(1));
            int t2 = Integer.parseInt(e2.get(1));

            String eType1 = e1.get(0);
            String eType2 = e2.get(0);

            if(t1 != t2) {
                return t1-t2;
            }
            else {
                if(eType1.equals("MESSAGE") && eType2.equals("OFFLINE")) {
                    return 1;
                }
                else {
                    return -1;
                }

                // return eType2.compareTo(eType1);
            }
        });



        // process each event
        for(List<String> event : events) {
            String eType = event.get(0);

            if(eType.equals("MESSAGE")) {
                handleMessage(event, mentions, offlineTime);
            }
            else {
                handleOffline(event, offlineTime);
            }
        }

        return mentions;
    }

    public void handleMessage(List<String> event, int[] mentions, int[] offlineTime) 
    {
        int n = mentions.length;
        String[] tokens = event.get(2).split(" ");
        int t = Integer.parseInt(event.get(1));
        for(String token : tokens) {
        
            if(token.equals("ALL")) {
                for(int i=0; i<n; i++) {
                    mentions[i] += 1;
                }
            }
            else if(token.equals("HERE")) {
                // traverse offline time of each and check if it's online
                for(int i=0; i<n; i++) {
                    if(offlineTime[i] == 0 || offlineTime[i] + 60 <= t) {
                        mentions[i] += 1;
                    }
                }
            }
            else {
                int id = Integer.parseInt(token.substring(2));
                mentions[id] += 1;
            }
        }
    }

    public void handleOffline(List<String> event, int[] offlineTime) 
    {
        int id = Integer.parseInt(event.get(2));
        int t = Integer.parseInt(event.get(1));

        offlineTime[id] = t;
    }


}