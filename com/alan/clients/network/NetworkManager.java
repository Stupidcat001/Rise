package com.alan.clients.network;

import by.radioegor146.nativeobfuscator.Native;
import com.alan.clients.util.interfaces.InstanceAccess;
import lombok.Getter;
import lombok.Setter;

@Getter
@Native
public final class NetworkManager implements InstanceAccess {
    @Setter
    public String username;
    public String message;

    public void init(String username) {
        this.username = username;
    }

    public static boolean a() {
        return true;
    }
}
