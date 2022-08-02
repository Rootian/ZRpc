package com.rootian.rpc.core;

import lombok.Data;

import java.util.Arrays;

/**
 * @Description:
 * @Author Rootian
 * @Date 2022-08-01
 */
@Data
public class RpcInvocation {

    private long id;

    private String serviceName;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] arguments;


    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes == null ? new Class<?>[0] : parameterTypes;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments == null ? new Object[0] : arguments;
    }

    @Override
    public String toString() {
        return "RpcInvocation [methodName=" + methodName + ", parameterTypes="
                + Arrays.toString(parameterTypes) + ", arguments=" + Arrays.toString(arguments)
                + "]";
    }
}
