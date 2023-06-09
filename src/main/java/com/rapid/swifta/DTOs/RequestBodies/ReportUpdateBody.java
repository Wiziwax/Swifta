package com.rapid.swifta.DTOs.RequestBodies;

import lombok.Data;

@Data
public class ReportUpdateBody {

    private boolean isIgnored;
    private boolean isTreated;
}
