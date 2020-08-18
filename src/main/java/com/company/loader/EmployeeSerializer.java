package com.company.loader;

import com.company.model.Employee;
import com.company.model.EmployeeType;
import com.google.gson.*;

import java.lang.reflect.Type;

public class EmployeeSerializer implements JsonSerializer<Employee>, JsonDeserializer<Employee> {

    private final String HOURLY_EMPLOYEE_TYPE = "hourly";
    private final String MONTHLY_EMPLOYEE_TYPE = "monthly";

    private final String TYPE_KEY = "type";
    private final String ID_KEY = "id";
    private final String NAME_KEY = "name";
    private final String RATE_KEY = "rate";

    public EmployeeSerializer() {
    }

    @Override
    public JsonElement serialize(Employee employee, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(TYPE_KEY, getEmployeeTypeString(employee));
        jsonObject.addProperty(ID_KEY, employee.getId());
        jsonObject.addProperty(NAME_KEY, employee.getName());
        jsonObject.addProperty(RATE_KEY, employee.getRate());
        return jsonObject;
    }

    @Override
    public Employee deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String typeValue = jsonObject.get(TYPE_KEY).getAsString();

        EmployeeType employeeType = getEmployeeType(typeValue);
        String id = jsonObject.get(ID_KEY).getAsString();
        String name = jsonObject.get(NAME_KEY).getAsString();
        double rate = jsonObject.get(RATE_KEY).getAsDouble();
        return new Employee(employeeType, id, name, rate);
    }

    private String getEmployeeTypeString(Employee e) {
        switch (e.getType()) {

            case MONTHLY:
                return MONTHLY_EMPLOYEE_TYPE;

            case HOURLY:
                return HOURLY_EMPLOYEE_TYPE;

            default:
                throw new IllegalStateException("Unknown employee type");
        }
    }

    private EmployeeType getEmployeeType(String type) {
        switch (type) {
            case HOURLY_EMPLOYEE_TYPE:
                return EmployeeType.HOURLY;
            case MONTHLY_EMPLOYEE_TYPE:
                return EmployeeType.MONTHLY;
            default:
                throw new IllegalStateException("Unknown employee type");
        }
    }
}
