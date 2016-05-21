package de.goldmann.comercio.client;


public class OrderMonitoringJobImpl implements OrderMonitoringJob
{
    private long checkInterVall = 6000;

    public OrderMonitoringJobImpl()
    {

    }

    @Override
    public void execute()
    {
        String url = "http://localhost:8080/orders";// "http://localhost:8080/person/search/findByLastName?name=Baggins1";
        //
        // final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        //
        // System.out.println("Open Orders:");
        // String body = response.getBody();
        //
        // if (body != null && body.length() > 2)
        // {
        // ObjectMapper mapper = new ObjectMapper();
        // try
        // {
        // List<LinkedHashMap<String, String>> orders = mapper.readValue(body, List.class);
        // System.out.println(orders.getClass());
        //
        // for (LinkedHashMap<String, String> orderRow : orders)
        // {
        // System.out.println(orderRow);
        // // TODO write to Textfields
        // /*
        // *
        // * Iterator<Entry<String, String>> it = orderRow.entrySet().iterator(); while
        // * (it.hasNext()) { System.out.println(it.next()); }
        // */
        // }
        // }
        // catch (JsonParseException e)
        // { // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // catch (JsonMappingException e)
        // {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // catch (IOException e)
        // {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
    }

    @Override
    public long getInterval()
    {
        return checkInterVall;
    }

    @Override
    public String getName()
    {
        return getClass().getSimpleName();
    }

    @Override
    public long getStartAfter()
    {
        return 6000;
    }

    @Override
    public String getTriggerName()
    {
        return getClass().getSimpleName() + "_trigger";
    }

}
