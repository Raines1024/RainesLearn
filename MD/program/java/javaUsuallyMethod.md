//集合转成字符串逗号分隔

    StringUtils.join(list,",");

//取集合中最大值

    long parValueMax = Collections.max(resultList.stream().map(OmsCouponUserAppVo::getParValue).collect(Collectors.toList()));

//字符串根据逗号转成List

    List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
//集合去重

    items.stream().distinct().collect(Collectors.joining(","));
//对对象集合中的某字段降序排序

    Collections.sort(list, new Comparator<OmsCouponSend>() {
        @Override
        public int compare(OmsCouponSend o1, OmsCouponSend o2) {
            if (o1.getCoupon() > o2.getCoupon()) {
                return -1;
            }
            if (o1.getCoupon().equals(o2.getCoupon())) {
                return 0;
            }
            return 1;
        }
    });
//对集合中某元素去重

    ArrayList<OmsCollectAppVo> distinctLiost = resultList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(OmsCollectAppVo::getSortId))), ArrayList::new));

//多条件对集合排序
(如果a1-a2大于0，返回正数是升序;如果a2-a1小于0，返回负数是降序)
    
    Collections.sort(students, new Comparator(){
    
      public int compare(Student a1, Student a2) {
    
     	int x = a1.getAge() - a2.getAge();
    
      int y = a1.getSalary() - a2.getSalary();
    
     	int z = a1.getHeight() - a2.getHeight();
    
      if(x==0){
    
      if(y==0){
    
      return z;
    
      }
    
      return y;
    
      }
    
      return x;
    
      }
    
      });
    
     
















