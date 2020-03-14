function getJsonValue(json, key) {
    if (key == null || key == "")
        return null;
    
    let value = json;
    let r = key.split(".");
    for (let i of r) {
        value = value[i];

        if (value == null)
            return null;
            
        if (typeof(value) == "string")
            return value;
    }

    return value;
}

export { getJsonValue };
