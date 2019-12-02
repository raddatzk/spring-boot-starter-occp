package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.client.*;
import com.valtech.springframework.ocpp.server.ServerCoreEventHandlerAdapter;
import com.valtech.springframework.ocpp.server.ServerEventsAdapter;
import com.valtech.springframework.ocpp.server.ServerFirmwareManagementEventHandlerAdapter;

public abstract class OcppTestBase {

    public static class ClientCoreEventHandler implements ClientCoreEventHandlerAdapter {
    }

    public static class ClientFirmwareManagementEventHandler implements ClientFirmwareManagementEventHandlerAdapter {
    }

    public static class ClientLocalAuthListEventHandler implements ClientLocalAuthListEventHandlerAdapter {

    }

    public static class ClientRemoteTriggerEventHandler implements ClientRemoteTriggerEventHandlerAdapter {

    }

    public static class ClientReservationEventHandler implements ClientReservationEventHandlerAdapter {

    }

    public static class ClientSmartChargingEventHandler implements ClientSmartChargingEventHandlerAdapter {

    }

    public static class ClientEvents implements ClientEventsAdapter {
    }

    public static class ServerCoreEventHandler implements ServerCoreEventHandlerAdapter {
    }

    public static class ServerEvents implements ServerEventsAdapter {
    }

    public static class ServerFirmwareManagementEventHandler implements ServerFirmwareManagementEventHandlerAdapter {
    }
}
