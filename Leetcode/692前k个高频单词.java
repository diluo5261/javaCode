
                    //2、频率不同，频率大的入堆
                    if(top.getValue() < entry.getValue()){
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }
            }
        }

        //4、将堆中的数据，存储到ret当中
        for(int i=0; i<k; i++){
            Map.Entry<String,Integer> top = minHeap.poll();
            if(top != null){
                ret.add(top.getKey());
            }
        }

        Collections.reverse(ret);
        return ret;
    }