package com.vmc.sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.vmc.sandbox.payroll.payment.attachment.TimeCard;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTimeCard is a Querydsl query type for TimeCard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTimeCard extends EntityPathBase<TimeCard> {

    private static final long serialVersionUID = -659563921L;

    public static final QTimeCard timeCard = new QTimeCard("timeCard");

    public final DateTimePath<org.joda.time.DateTime> date = createDateTime("date", org.joda.time.DateTime.class);

    public final NumberPath<Integer> hours = createNumber("hours", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QTimeCard(String variable) {
        super(TimeCard.class, forVariable(variable));
    }

    public QTimeCard(Path<? extends TimeCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeCard(PathMetadata metadata) {
        super(TimeCard.class, metadata);
    }

}

