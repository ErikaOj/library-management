package management;

import datastructures.maps.CustomHashMap;
import library.Member;
import library.Transaction;

public class MemberManager {
    private CustomHashMap<String, Member> members;

    public MemberManager() {
        this.members = new CustomHashMap<>();
    }

    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        if (member.getMemberId() == null || member.getMemberId().trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        members.put(member.getMemberId(), member);
    }

    public Member getMember(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        return members.get(memberId);
    }

    public void recordTransaction(String memberId, Transaction transaction) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        Member member = getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found");
        }

        member.addTransaction(transaction);
    }

    public Transaction getLastTransaction(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be null or empty");
        }

        Member member = getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found");
        }

        return member.getLastTransaction();
    }

}